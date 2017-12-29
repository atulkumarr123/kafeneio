package com.kafeneio.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;

@Repository
public class FoodDAO {
	
	@Inject
	EntityManager entityManager;
	
	public List<FoodItems> editFoodItems(FoodItems foodItems) {
		StringBuffer queryStr = new StringBuffer("select foodItems  from FoodItems foodItems");
		if(foodItems.getFoodCategory() != null && !"".equals(foodItems.getFoodCategory())){
			FoodCategory category = foodItems.getFoodCategory();
			queryStr.append(" where foodItems.foodCategory.id = "+category.getId());
		}
		if(foodItems.isStatus()){
			queryStr.append(" and foodItems.status = 1");
		}
		if(!foodItems.isStatus()){
			queryStr.append(" and foodItems.status = 0");
		}
		
		Query query = entityManager.createQuery(queryStr.toString());
		List <FoodItems> orders = query.getResultList();
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
