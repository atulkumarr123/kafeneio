package com.kafeneio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.service.FoodService;

@RestController
public class FoodController extends BaseRestController {


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
		FoodCategory foodCategory=new FoodCategory();
		foodCategory.setId(1L);
		foodCategory.setFoodCode("KC");
		foodCategory.setFoodDesc("Kafeneio Coolers");
		
		FoodCategory foodCategory1=new FoodCategory();
		foodCategory1.setId(1L);
		foodCategory1.setFoodCode("KC");
		foodCategory1.setFoodDesc("Kafeneio Coolers");
		
		
		List<FoodCategory> list=new ArrayList<FoodCategory>();
		list.add(foodCategory);
		list.add(foodCategory1);
		modelMap.put("categoryList",list);
		modelMap.put("foodCategory", foodCategory);
		return "addMenuItems";
	}
}
