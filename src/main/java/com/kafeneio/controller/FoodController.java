package com.kafeneio.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.service.FoodItemsService;
import com.kafeneio.service.FoodService;

@RestController
public class FoodController {
	
	@Inject
	FoodItemsService foodItemsService;

	@Inject
	FoodService foodService;

	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/foodItems", method = RequestMethod.POST)
	public MessageDTO saveFoodItems(@RequestBody List<FoodItemsDto> foodItems, @RequestParam("categoryId") Long categoryId)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		foodItems.stream().forEach(foodItem -> foodItem.setId(null));
		MessageDTO msgDTO = null;
		msgDTO =foodItemsService.saveFoodItems(foodItems,categoryId);
		return msgDTO;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/editFoodItems",method=RequestMethod.POST)
	public List<FoodItemsDto> editFoodItems(@RequestBody FoodItemsDto foodItemsDto)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<FoodItemsDto> items = foodService.editFoodItems(foodItemsDto);
		return items;

}
}


@Controller
class FoodLoaderController{
	@Inject
	FoodService foodService;

	@RequestMapping(value = "/foodCategory")
	public String foodCategory(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<FoodCategory> foodList = foodService.findFoodCategory();
		modelMap.put("foodList",foodList);
		modelMap.put("fileName","foodCategory");
		return "index";
	}

	@RequestMapping(value = "/billingHome")
	public String billingHome(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "index2";
	}

	@RequestMapping(value = "/addMenuItems")
	public String addMenuItems(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		
		List<FoodCategory> foodList = foodService.findFoodCategory();
		
		modelMap.put("categoryList",foodList);
		FoodCategory foodCategory = new FoodCategory();
		modelMap.put("foodCategory", foodCategory);
		return "addMenuItems";
	}
}
 