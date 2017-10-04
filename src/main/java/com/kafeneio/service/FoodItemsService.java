package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.FoodItems;

public interface FoodItemsService {
	
	public MessageDTO saveFoodItems(List<FoodItems> foodItems,Long categoryId);
}
