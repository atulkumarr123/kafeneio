package com.kafeneio.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.FoodItemsDto;
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
	public MessageDTO saveFoodItems(List<FoodItemsDto> foodItemsDto, Long categoryId) {
		MessageDTO msgDTO = new MessageDTO();
		List<FoodItems> foodItems = new ArrayList<FoodItems>();
		try{
			FoodCategory foodCategory=foodRepository.findById(categoryId);
			foodItemsDto.forEach(foodItemDto ->{
				FoodItems foodItem = new FoodItems();
				foodItem.setAmount(foodItemDto.getAmount());
				foodItem.setFoodItemCode(foodItemDto.getFoodItemCode());
				foodItem.setFoodItemDesc(foodItemDto.getFoodItemDesc());
				foodItem.setStatus(foodItemDto.isStatus());
				foodItem.setDate(new Date());
				foodItem.setFoodCategory(foodCategory);
				foodItems.add(foodItem);
			});
			
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

	@Override
	public List<FoodItemsDto> fetchFoodItems() {
		List<FoodItems> itemsList =   foodRepository.fetchFoodItems();
		List<FoodItemsDto> dtoList = new ArrayList<FoodItemsDto>();
		itemsList.forEach(foodItem ->{
			FoodItemsDto dto = new FoodItemsDto();
			dto.setAmount(foodItem.getAmount());
			dto.setDate(foodItem.getDate());
			dto.setFoodCategoryDesc(foodItem.getFoodCategory().getFoodDesc());
			dto.setFoodItemCode(foodItem.getFoodItemCode());
			dto.setFoodItemDesc(foodItem.getFoodItemDesc());
			dto.setId(foodItem.getId());
			dto.setStatus(foodItem.isStatus());
			
			dtoList.add(dto);
		});	
		return dtoList;
	}
}
