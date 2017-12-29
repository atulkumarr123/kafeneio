package com.kafeneio.service;

import java.util.List;
import java.util.Set;

import com.kafeneio.model.Expenses;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;

public interface FoodService {
	List<FoodCategory> findFoodCategory();
	List<FoodItems> findFoodItems(String category);
	List<Object> switchCaseDemo();
	List<FoodItems> editFoodItems(FoodItems foodItems);

}
