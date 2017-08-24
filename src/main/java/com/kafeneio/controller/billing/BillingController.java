package com.kafeneio.controller.billing;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.enums.ResponseKeyName;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;
import com.kafeneio.service.BillingService;
import com.kafeneio.service.FoodService;

@RestController
public class BillingController {
	@Inject
	FoodService foodService;
	@Inject
	BillingService billingService;
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
	public ResponseEntity<Order> generateBill(@RequestBody Order order)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		ResponseEntity<Order> response = null;
		boolean isSaved = billingService.saveOrder(order);
		if(isSaved){
			response = new ResponseEntity<Order>(order, HttpStatus.OK);
		}
		else{
			response = new ResponseEntity<Order>(order, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@RequestMapping(value="/MaxOrderNo",method=RequestMethod.GET)
	public Long getNextOrderNo()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		Long maxOrderNo = billingService.getOrderNo();
		return maxOrderNo;
	}
}
