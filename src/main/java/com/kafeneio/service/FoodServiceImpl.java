package com.kafeneio.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.repository.FoodRepository;
import com.kafeneio.repository.EditFoodItemsRepository;

@Service
@Qualifier(value="foodService")
public class FoodServiceImpl extends BaseServiceImpl implements FoodService{
	
	
	@Inject
	FoodRepository foodRepository;
	
	@Inject
	EditFoodItemsRepository editFoodItemsRepository;
	
	
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
	public List<FoodItems> editFoodItems() {
		List<FoodItems> items =   editFoodItemsRepository.editFoodItems();
		return items;
	}
	
}
