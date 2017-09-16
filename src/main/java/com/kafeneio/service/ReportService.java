package com.kafeneio.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;

public interface ReportService {
	
	public List<Order> fetchOrders(String fromDate, String toDate);
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Expenses> fetchExpenses(String fromDate, String toDate);
}
