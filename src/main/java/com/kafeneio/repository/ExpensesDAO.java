package com.kafeneio.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import com.kafeneio.model.Expenses;

@Repository
public class ExpensesDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<Expenses> fetchExpenses(Date date, Long type) {
		Date todayMorning = DateUtils.truncate(date, Calendar.DATE);
		Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59), 59);
	
		StringBuffer queryStr = new StringBuffer("select expenses from Expenses expenses ");
		if(date !=null){
			queryStr.append("where CREATION_DATE between :fromDate and :toDate");
			}
		if(type != null && type != 0){
			queryStr.append(" and expenses.expenseType.id ="+type);
		}
		Query query = entityManager.createQuery(queryStr.toString());
		
		if(date !=null){
			query.setParameter("fromDate", todayMorning);
			query.setParameter("toDate", todayEvening);
		}
		List <Expenses> expenses = query.getResultList();
		return expenses;
	}
}
