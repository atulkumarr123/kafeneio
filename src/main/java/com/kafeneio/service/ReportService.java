package com.kafeneio.service;

import java.util.List;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;

public interface ReportService {
	
	public List<Order> fetchOrders(String fromDate, String toDate);
	public List<Expenses> fetchExpenses(String fromDate, String toDate);
}
