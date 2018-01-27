package com.kafeneio.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.repository.FoodRepository;

@Repository
public class FoodDAO {
	
	@Inject
	EntityManager entityManager;
	
	@Inject
	FoodRepository foodRepository;
	
	public List<FoodItems> editFoodItems(FoodItemsDto foodItemsDto) {
		
		StringBuffer queryStr = new StringBuffer("select foodItems  from FoodItems foodItems");
		if(foodItemsDto.getFoodCategoryId() != null && !"".equals(foodItemsDto.getFoodCategoryId())){

			queryStr.append(" where foodItems.foodCategory.id = "+foodItemsDto.getFoodCategoryId());
		}
		if(foodItemsDto.isStatus()){
			queryStr.append(" and foodItems.status = 1");
		}
		if(!foodItemsDto.isStatus()){
			queryStr.append(" and foodItems.status = 0");
		}
		
		Query query = entityManager.createQuery(queryStr.toString());
		List<FoodItems> items = query.getResultList();
			
		
		return items;
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
