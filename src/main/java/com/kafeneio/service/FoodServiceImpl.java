package com.kafeneio.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.dao.FoodDAO;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.repository.FoodItemsRepository;
import com.kafeneio.repository.FoodRepository;

@Service
@Qualifier(value="foodService")
public class FoodServiceImpl extends BaseServiceImpl implements FoodService{
	
	
	@Inject
	FoodRepository foodRepository;
	
	@Inject
	FoodItemsRepository foodItemsRepository;
	
	@Inject
	FoodDAO foodDao;
	
	
	@Override
	public List<FoodCategory> findFoodCategory() {
		List<FoodCategory> categories = foodRepository.findFoodCategory("Active");
		/*Map<String,String> foodCatMap=new HashMap<String,String>();
		Iterator<FoodCategory> itr=categories.iterator();
		if(itr.hasNext()){
			FoodCategory foodCategory = itr.next();
			foodCatMap.put(foodCategory.getFoodCode(),foodCategory.getFoodDesc());	
		}*/
		return categories;
	}
	@Override
	public List<Object> switchCaseDemo() {
		List<Object> values = foodRepository.switchCaseDemo();
		return values;
	}
	
	@Override
	public List<FoodItems> findFoodItems(String category) {
		List<FoodItems> items =   foodRepository.findFoodItemsForCategory(category);
		Comparator<FoodItems> byId = (FoodItems item1, FoodItems item2)->item1.getId().compareTo(item2.getId());
		Collections.sort(items, byId);
		return items;
	}
	
	@Override
	public List<FoodItemsDto> editFoodItems(FoodItemsDto foodItemsDto) {
		// category = foodItems.getFoodCategory();

		List<FoodItems> items =   foodDao.editFoodItems(foodItemsDto);
		List<FoodItemsDto> dtoList = new ArrayList<FoodItemsDto>();
		for(FoodItems item: items){
			FoodItemsDto dto = new FoodItemsDto();
			dto.setAmount(item.getAmount());
			dto.setDate(item.getDate());
			dto.setFoodCategoryDesc(item.getFoodCategory().getFoodDesc());
			dto.setFoodItemCode(item.getFoodItemCode());
			dto.setFoodItemDesc(item.getFoodItemDesc());
			dto.setStatus(item.isStatus());
			dto.setStatusVisible(item.isStatus()?ApplicationConstant.ACTIVE:ApplicationConstant.INACTIVE);
			dtoList.add(dto);
		}	
		return dtoList;
	}
	
}
