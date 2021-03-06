package com.kafeneio.service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.ExpenseType;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.repository.ModeOfPaymentRepository;
import com.kafeneio.repository.OrderDAO;
import com.kafeneio.repository.OrderRepository;
import com.kafeneio.repository.ReportDAO;

@Service
public class ReportServiceImpl extends BaseServiceImpl implements ReportService{

	@Inject
	ReportDAO reportDao;
	
	@Inject
	OrderDAO orderDao;
	
	@Inject
	OrderRepository orderRepository;
	
	@Inject
	ModeOfPaymentRepository modeOfPaymentRepository;
	
	
	@Override
	public List<Order> fetchOrders(String fromDate, String toDate, String modes) {
		List<Order> orders = null;
		DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		Date fromDate1;
		try {
			fromDate1 = format.parse(fromDate);
			Date toDate1 = format.parse(toDate);
			orders = reportDao.fetchOrders(fromDate1, toDate1, modes);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	@Override
	public List<Expenses> fetchExpenses(String fromDate, String toDate, Long expenseType) {
		List<Expenses> expenses = null;
		DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		Date fromDate1;
		try {
			fromDate1 = format.parse(fromDate);
			Date toDate1 = format.parse(toDate);
			expenses = reportDao.fetchExpenses(fromDate1, toDate1, expenseType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return expenses;
	}
	
	@Override
	public List<Order> getOrderList(String status,String date) {
		List<Order> orders = null;
		DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		try{
			Date tempDate = format.parse(date);
			orders = orderDao.getOrderList(status,tempDate);
			if(status.equals(ApplicationConstant.NEW_ORDER)){
				orders.forEach(order->{
					DateFormat format1 = new SimpleDateFormat(ApplicationConstant.TIME_FORMAT);
					String time = format1.format(order.getCreationDate());
					order.setOrderTime(time);
					if(order.getGstAmount() != null){
					OrderDetails orderDetail = new OrderDetails();
					orderDetail.setFoodDesc("GST");
					orderDetail.setAmount(order.getGstAmount());
					order.getOrderDetails().add(orderDetail);
					}
				});
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return orders;
	}


	@Override
	public List<ModeOfPayment> findModeOfPayment() {
		List<ModeOfPayment> modes = modeOfPaymentRepository.findModeOfPayment();
		return modes;
	}
	
	
}
 