package com.kafeneio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;

@Repository
public class ExpensesDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<Expenses> fetchExpenses() {
		Query query = entityManager.createQuery("select expenses from Expenses expenses");
		List <Expenses> expenses = query.getResultList();
		return expenses;
	}
}
