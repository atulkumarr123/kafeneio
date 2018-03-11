package com.kafeneio.service;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.enums.AppConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderStatus;
import com.kafeneio.repository.ModeOfPaymentRepository;
import com.kafeneio.repository.OrderRepository;
import com.kafeneio.repository.OrderStatusRepository;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService{
	
	
	@Inject
	OrderRepository orderRepository;
	
	@Inject
	OrderStatusRepository orderStatusRepository;
	

	@Inject
	ModeOfPaymentRepository modeOfPaymentRepository;

	@Inject 
	InventoryService inventoryService;
	
	private final Logger logger =
			LoggerFactory.getLogger(this.getClass());
	@Override
	public List<MessageDTO> serve(Long orderId, Long mopId){
		List<MessageDTO> msgDTOList = null;
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus servedStatus = orderStatusRepository.findByCode(ApplicationConstant.SERVED_ORDER);
			order.setStatus(servedStatus);
			if(mopId != null){
				ModeOfPayment modeOfPayment = modeOfPaymentRepository.findOne(mopId);
				order.setModeOfPayment(modeOfPayment);
			}
			orderRepository.save(order);
			// 1. Call inventory service to reduce raw materials corresponding to food items of this order.
			 msgDTOList = inventoryService.settleInventory(order,ApplicationConstant.SUBTRACT);
			 MessageDTO msgDTO = new MessageDTO();
			 msgDTO.setMessage("Order "+order.getOrderNo()+" served successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
			msgDTO.setMessageType(AppConstant.SUCCESS);
			msgDTOList.add(msgDTO);
		}
		catch(Exception exception){
			if(exception instanceof KafeneioException){
				KafeneioException kafeException = (KafeneioException)exception;
				String message = kafeException.getMessage();
				setInternalServerErrorMsg(msgDTOList, message);
			}
			else{
				String message = "Some Error";
				setInternalServerErrorMsg(msgDTOList, message);
			}
		}
		return msgDTOList;
	}


	private void setInternalServerErrorMsg(List<MessageDTO> msgDTOList, String message) {
		MessageDTO msgDTO = new MessageDTO();
		msgDTO.setMessage(message);
		msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		msgDTO.setMessageType(AppConstant.ERROR);
		msgDTOList.add(msgDTO);
	}


	@Override
	public MessageDTO cancel(Long orderId, String reason, Boolean isInventoryUpdate) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus cancelledStatus = orderStatusRepository.findByCode(ApplicationConstant.CANCELLED_ORDER);
			order.setStatus(cancelledStatus);
			order.setReason(reason);
			if(isInventoryUpdate){
				inventoryService.settleInventory(order,ApplicationConstant.SUBTRACT);

			}
			msgDTO.setMessage("Order "+order.getOrderNo()+" cancelled successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	@Override
	public MessageDTO reInitiate(Long orderId) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus cancelledStatus = orderStatusRepository.findByCode(ApplicationConstant.NEW_ORDER);
			order.setStatus(cancelledStatus);
			orderRepository.save(order);
			logger.debug("Gong to update inventory on re Intiate");
			inventoryService.settleInventory(order,ApplicationConstant.ADD);
			msgDTO.setMessage("Order "+order.getOrderNo()+" reinitiated successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	


	@Override
	public MessageDTO seatIt(Long orderId, String table) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			order.setTable(table);
			orderRepository.save(order);
			msgDTO.setMessage("Seated!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}


	@Override
	public List<ModeOfPayment> findMOPs() {
		List<ModeOfPayment> mops = modeOfPaymentRepository.findAll();
		return mops;
	}


	@Override
	public Order findOrder(Long orderId) {
		Order order=orderRepository.findOne(orderId);
		return order;
	}
	
}
