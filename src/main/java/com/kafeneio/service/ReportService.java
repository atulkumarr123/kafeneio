package com.kafeneio.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderStatus;

public interface ReportService {
	
	public List<Order> fetchOrders(String fromDate, String toDate, String modes);
	
	public List<Order> getOrderListToday(String status);

	
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Expenses> fetchExpenses(String fromDate, String toDate);

	public List<ModeOfPayment> findModeOfPayment();
}
