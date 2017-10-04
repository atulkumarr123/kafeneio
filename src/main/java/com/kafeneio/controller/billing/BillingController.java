package com.kafeneio.controller.billing;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
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
	public List<FoodItems> getFoods(@PathVariable String category)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<FoodItems> items = foodService.findFoodItems(category);
		return items;
	}

	
	
	@RequestMapping(value = "/generateBill", method = RequestMethod.POST)
	public MessageDTO generateBill(@RequestBody Order order)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = billingService.saveOrder(order);
		return msgDTO;
	}
	@RequestMapping(value="/maxOrderNo",method=RequestMethod.GET)
	public Long getNextOrderNo()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		Long maxOrderNo = billingService.getOrderNo();
		return maxOrderNo;
	}
}
