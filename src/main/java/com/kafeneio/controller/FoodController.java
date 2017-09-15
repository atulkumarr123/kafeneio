package com.kafeneio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.service.FoodService;

@Controller
public class FoodController extends BaseRestController {

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
}