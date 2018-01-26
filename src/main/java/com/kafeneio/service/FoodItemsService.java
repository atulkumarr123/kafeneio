package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.DTO.MessageDTO;

public interface FoodItemsService {
	
	
	public MessageDTO saveFoodItems(List<FoodItemsDto> foodItems,Long categoryId);
}
