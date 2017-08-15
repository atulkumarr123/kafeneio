package com.kafeneio.controller.billing;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.service.FoodService;

@RestController
public class BillingController {
	
	@Inject
	FoodService foodService;
	
	@RequestMapping(value="/food/{category}",method=RequestMethod.GET)
	public Set<FoodItems> getFoods(@PathVariable String category)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		Set<FoodItems> items = foodService.findFoodItems(category);
		return items;
	}
	
}
