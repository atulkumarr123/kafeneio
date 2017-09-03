package com.kafeneio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;
import com.kafeneio.service.ExpensesService;

@Repository
public class ReportDAO {
	
	public List<Order> fetchOrders(Date fromDate, Date toDate) {
		return null;
	}
	
	public List<Expenses> fetchExpenses(Date fromDate, Date toDate) {
		return null;
	}
}
