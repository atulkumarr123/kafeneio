package com.kafeneio.service;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.repository.FoodItemsRepository;
import com.kafeneio.repository.FoodRepository;

@Service
public class FoodItemsServiceImpl extends BaseServiceImpl implements FoodItemsService{
	@Inject
	FoodItemsRepository foodItemsRepository;
	
	@Inject
	FoodRepository foodRepository;
	
	@Override
	public MessageDTO saveFoodItems(List<FoodItems> foodItems, Long categoryId) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			FoodCategory foodCategory=foodRepository.findById(categoryId);
			for(FoodItems foodItem : foodItems){
				foodItem.setDate(new Date());
				foodItem.setFoodCategory(foodCategory);
			}
			foodItemsRepository.save(foodItems);
			msgDTO.setMessage("Success");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
}
