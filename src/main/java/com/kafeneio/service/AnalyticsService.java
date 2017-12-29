package com.kafeneio.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.model.OrderStatus;

public interface AnalyticsService {
	
	public List<OrderDetails> fetchOrders(String fromDate, String toDate, String modes, String category, String foodItems);
	
//	public List<Order> getOrderListToday(String status);

	public List<ModeOfPayment> findModeOfPayment();
}
