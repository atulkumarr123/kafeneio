package com.kafeneio.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;

@Repository
public class AnalyticsDAO {

	@Inject
	EntityManager entityManager;
	
	@Inject
	OrderRepository orderRepository;


	public List<OrderDetails> fetchSoldItems(Date fromDate, Date toDate, String modes, String category, String foodItems) {
		//		Date today = new Date();
				Date fDate = DateUtils.truncate(fromDate, Calendar.DATE);
				Date tDate = DateUtils.truncate(toDate, Calendar.DATE);
		//		Date todayEvening = DateUtils.addSeconds(DateUtils.addMinutes(DateUtils.addHours(todayMorning, 23), 59), 59);
		StringBuffer queryStr = new StringBuffer("select orderDetails from OrderDetails orderDetails where orderDetails.creationDate  between :fromDate and :toDate");
		if(category!=null && !category.isEmpty() && !category.equals("0")){	 
			queryStr.append(" and orderDetails.foodCode in (select foodItems.foodItemCode from FoodItems foodItems where foodItems.foodCategory.id = "+category+")");
		}	
		if(foodItems != null && !foodItems.isEmpty()){
			queryStr.append(" and orderDetails.foodCode in (select foodItems.foodItemCode from FoodItems foodItems where id in ("+foodItems+"))");
		}

		if(modes!=null && !modes.isEmpty()){
			queryStr.append(" and orderDetails.order.modeOfPayment.id in ("+modes+"))");

		}

		Query query = entityManager.createQuery(queryStr.toString());
		query.setParameter("fromDate", fDate);
		query.setParameter("toDate", tDate);
		//StringBuffer queryStr = new StringBuffer("select orderDetails from OrderDetails orderDetails where between :fromDate and :toDate " +" and orderDetails.foodCode in (select foodItems.foodItemCode from FoodItems foodItems where foodItems.foodCategory.id = :category)");

		/*Query query = entityManager.createQuery(queryStr.toString());
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);		
		 *///query.setParameter("category", Long.parseLong(category));
		List <OrderDetails> orderDetails = query.getResultList();
		return orderDetails;
	}
	
}
