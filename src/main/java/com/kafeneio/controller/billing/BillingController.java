package com.kafeneio.controller.billing;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;
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
	public MessageDTO generateBill(@RequestBody Order order, @RequestParam(value = "mopId", required = false) Long mopId,
	@RequestParam(value = "date", required = true) String date)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = billingService.saveOrder(order, mopId, date);
		return msgDTO;
	}
	@RequestMapping(value="/maxOrderNo",method=RequestMethod.GET)
	public Long getNextOrderNo( @RequestParam(value = "date", required = true) String date)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		Long maxOrderNo = null;
		try {
			maxOrderNo = billingService.getOrderNo(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return maxOrderNo;
	}
}
