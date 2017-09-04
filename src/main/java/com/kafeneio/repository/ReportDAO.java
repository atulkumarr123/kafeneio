package com.kafeneio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.Order;
import com.kafeneio.service.ExpensesService;

@Repository
public class ReportDAO {

	@Inject
	ExpensesService expensesService;
	
	@Inject
	EntityManager entityManager;
	
	
	public List<Order> fetchOrders(Date fromDate, Date toDate) {
		Query query = entityManager.createQuery("select order from Order order where trunc(creationDate) <= :toDate and trunc(creationDate) >=:fromDate");
//		int pageNumber = 1;
//		int pageSize = 10;
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
//		query.setFirstResult((pageNumber-1) * pageSize); 
//		query.setMaxResults(pageSize);
		List <Order> orders = query.getResultList();
		return orders;
	}
}
