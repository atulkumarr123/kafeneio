package com.kafeneio.controller.billing;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.AccountDTO;
import com.kafeneio.controller.BaseRestController;
import com.kafeneio.enums.ResponseKeyName;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.service.AccountService;
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
	
/*	@RequestMapping(value="/generateBill",method=RequestMethod.POST)
	public String generateBill(@RequestBody AccountDTO orders)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "Order saved Succesfuly!";
	}*/
	
	@RequestMapping(value = "/generateBill", method = RequestMethod.POST)
	public List<OrderDetails> generateBill(@RequestBody List<OrderDetails> orders)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return  orders;
	}
	
	
	
}
