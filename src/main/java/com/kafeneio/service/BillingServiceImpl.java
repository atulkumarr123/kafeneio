package com.kafeneio.service;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.InventoryRules;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.model.OrderStatus;
import com.kafeneio.repository.FoodItemsRepository;
import com.kafeneio.repository.InventoryRepository;
import com.kafeneio.repository.ModeOfPaymentRepository;
import com.kafeneio.repository.OrderDAO;
import com.kafeneio.repository.OrderRepository;
import com.kafeneio.repository.OrderStatusRepository;
import com.kafeneio.repository.RawMaterialRepository;

@Service
public class BillingServiceImpl extends BaseServiceImpl implements BillingService{
	
	@Inject
	OrderRepository orderRepository;
	
	@Inject
	OrderStatusRepository orderStatusRepository;

	@Inject
	ModeOfPaymentRepository modeOfPaymentRepository;
	
	@Inject
	FoodItemsRepository foodItemsRepository;
	
	@Inject 
	InventoryRepository inventoryRepository;
	
	@Inject 
	RawMaterialRepository rawMaterialRepository;
	
	
	@Inject
	OrderDAO orderDao;
	
	private final Logger logger =
			LoggerFactory.getLogger(this.getClass());
	
	@Override
	public synchronized MessageDTO saveOrder(Order order, Long mopId, String date){
		
		MessageDTO msgDTO = new MessageDTO();
		try{
			logger.info("Going to check inventory");
			msgDTO = checkInventory(order);
			if(msgDTO.getStatusCode() == HttpStatus.INSUFFICIENT_STORAGE.value()){
				logger.info("Inventory down...");
				return msgDTO;
			}
			order.setOrderNo(this.getOrderNo(date));
				if(isOrderExist(order.getOrderNo())){
					msgDTO.setMessage("Order <b>"+order.getOrderNo()+"</b> already taken, Change the order Number!");
					msgDTO.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
				}
				else{
					populateOrder(order,date);
					OrderStatus orderStatus = orderStatusRepository.findByCode(ApplicationConstant.NEW_ORDER);
					order.setStatus(orderStatus);
					if(mopId != null){
						ModeOfPayment modeOfPayment = modeOfPaymentRepository.findOne(mopId);
						order.setModeOfPayment(modeOfPayment);
					}
					
					orderRepository.save(order);
					msgDTO.setMessage("Order "+order.getOrderNo()+" saved Successfully!");
					msgDTO.setStatusCode(HttpStatus.OK.value());
				}
		}
		catch(Exception exception){
			exception.printStackTrace();
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}

	private MessageDTO checkInventory(Order order) {
		MessageDTO msgDTO = new MessageDTO();
		Set<OrderDetails> setOfdetails = order.getOrderDetails();
		setOfdetails.forEach(orderDetail -> {
			String foodCode = orderDetail.getFoodCode();
			FoodItems foodItem = foodItemsRepository.findByFoodItemCode(foodCode);
			List<InventoryRules> rules = inventoryRepository.findByFoodItems(foodItem);
			rules.forEach(rule -> {
				BigDecimal availableQuantity = rule.getRawMaterial().getQuantity();

				if (availableQuantity.compareTo(rule.getQuantity().multiply(new BigDecimal(orderDetail.getQuantity()))) < 0){
					msgDTO.setMessage(rule.getRawMaterial().getRawMaterialDesc()+ " is not available in the inventory,<br> Please update the inventory");
					msgDTO.setStatusCode(HttpStatus.INSUFFICIENT_STORAGE.value());
				}
			});
		});

		return msgDTO;
	}

	private boolean isOrderExist(Long orderNo) {
		List<Long> orders = orderDao.findRecentOrder();
		if(orders != null && !orders.isEmpty()){
			return orders.get(0).equals(orderNo);
		}
		else return false;
	}

	private void populateOrder(Order order, String date) throws ParseException {
		double amount = 0;
		//order.setOrderNo(orderNo);
		DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		if((date!=null && !date.isEmpty())){
			order.setCreationDate(format.parse(date));
		}
		else{
			order.setCreationDate(new Date());
		}
		Iterator<OrderDetails> itr = order.getOrderDetails().iterator();	
		while(itr.hasNext()){
			OrderDetails orderDetails= itr.next();
			amount=amount+((orderDetails.getAmount()!=null)?orderDetails.getAmount():0);
			orderDetails.setCreationDate(order.getCreationDate());	
		}
		order.setAmount(amount);
		if(order.getGstAmount() != null){
			order.setAmount(Double.sum(order.getAmount(),order.getGstAmount()));
		}
	}
	public Long getOrderNo(String date) throws ParseException{
		Long orderNo = orderDao.findOrderNo(date);
		orderNo=(orderNo!=null)?(orderNo+1):ApplicationConstant.BASE_ORDER_NO;
		return orderNo;
	}
}
