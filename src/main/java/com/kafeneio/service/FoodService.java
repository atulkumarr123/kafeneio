package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;

public interface FoodService {
	List<FoodCategory> findFoodCategory();
	List<FoodItems> findFoodItems(String category);
	List<Object> switchCaseDemo();
	List<FoodItemsDto> editFoodItems(FoodItemsDto foodItems);

}
