package com.kafeneio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.Order;
import com.kafeneio.service.ExpensesService;

@Repository
public class ReportDAO {

	@Inject
	ExpensesService expensesService;
	
	public List<Order> fetchOrders(Date fromDate, Date toDate) {
		return null;
	}
}
