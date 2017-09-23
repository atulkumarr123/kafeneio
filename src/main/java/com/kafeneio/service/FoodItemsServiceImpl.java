package com.kafeneio.service;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
	public boolean saveFoodItems(List<FoodItems> foodItems, Long categoryId) {
		boolean isSaved;
		try{
			FoodCategory foodCategory=foodRepository.findById(categoryId);
			for(FoodItems foodItem : foodItems){
				foodItem.setDate(new Date());
				foodItem.setFoodCategory(foodCategory);
			}
			foodItemsRepository.save(foodItems);
			isSaved = true;
		}
		catch(Exception exception){
			isSaved = false;
		}
		return isSaved;
	}

}
