package com.kafeneio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;

@Repository
public class ReportDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<Order> fetchOrders(Date fromDate, Date toDate) {
		Query query = entityManager.createQuery("select order from Order order where creationDate <= :toDate and creationDate >=:fromDate");
//		int pageNumber = 1;
//		int pageSize = 10;
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
//		query.setFirstResult((pageNumber-1) * pageSize); 
//		query.setMaxResults(pageSize);
		List <Order> orders = query.getResultList();
		return orders;
	}
	
	public List<Expenses> fetchExpenses(Date fromDate, Date toDate) {
		Query query = entityManager.createQuery("select expenses from Expenses expenses where creation_date <= :toDate and creation_date >=:fromDate");
//		int pageNumber = 1;
//		int pageSize = 10;
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
//		query.setFirstResult((pageNumber-1) * pageSize); 
//		query.setMaxResults(pageSize);
		List <Expenses> expenses = query.getResultList();
		return expenses;
	}
}
