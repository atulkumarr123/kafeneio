package com.kafeneio.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;

@Repository
public class OrderDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public Long findOrderNo() {
		Date today = new Date();
		Date todayMorning = DateUtils.truncate(today, Calendar.DATE);
		Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59), 59);
		Query query =  entityManager.createQuery("select MAX(orderNo) from Order order where order.creationDate  between :fromDate and :toDate");
		query.setParameter("fromDate", todayMorning);
		query.setParameter("toDate", todayEvening);
		Long orderNo = (Long) query.getSingleResult();
		return orderNo;
	}
	
	public List<Long> findRecentOrder() {
		Date today = new Date();
		Date todayMorning = DateUtils.truncate(today, Calendar.DATE);
		Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59), 59);
		Query query =  entityManager.createQuery("select ord.orderNo from Order ord where ord.creationDate  between :fromDate and :toDate order by id desc");
		query.setParameter("fromDate", todayMorning);
		query.setParameter("toDate", todayEvening);
		List <Long> orders = query.getResultList();
		return orders;
	}
	
	public List<Order> getOrderList(String status,Date date) {
		status = status.equalsIgnoreCase("STATUS_NEW")?ApplicationConstant.NEW_ORDER:status;
		Date morning = DateUtils.truncate(date, Calendar.DATE);
		Date evening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(morning, 23), 59), 59);
		Query query =  entityManager.createQuery("select ord from Order ord where ord.status.code=:status and ord.creationDate  between :fromDate and :toDate order by id desc");
		query.setParameter("fromDate", morning);
		query.setParameter("toDate", evening);
		query.setParameter("status", status);
		List <Order> orders = query.getResultList();
		return orders;
	}
	

}
