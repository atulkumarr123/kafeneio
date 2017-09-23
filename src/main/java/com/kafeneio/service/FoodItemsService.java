package com.kafeneio.service;

import java.util.List;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;

public interface FoodItemsService {
	
	public boolean saveFoodItems(List<FoodItems> foodItems,Long categoryId);
}
