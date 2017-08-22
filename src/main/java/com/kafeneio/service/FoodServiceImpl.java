package com.kafeneio.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.repository.FoodRepository;

@Service
public class FoodServiceImpl extends BaseServiceImpl implements FoodService{
	
	
	@Inject
	FoodRepository foodRepository;
	
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
	public Set<FoodItems> findFoodItems(String category) {
		Set<FoodItems> items =   foodRepository.findFoodItemsForCategory(category);
		return items;
	}
	
}
