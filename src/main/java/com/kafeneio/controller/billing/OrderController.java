package com.kafeneio.controller.billing;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {


	@Inject
	OrderService orderService;

	@RequestMapping(value = "/serve/{orderId}")
	public MessageDTO serve(@PathVariable Long orderId, @RequestParam(value = "mopId", required = false) Long mopId) {
		MessageDTO msgDTO = orderService.serve(orderId, mopId);
		return msgDTO;
	}
	
	@RequestMapping(value = "/cancel/{orderId}")
	public MessageDTO cancel(@PathVariable Long orderId) {
		MessageDTO msgDTO = orderService.cancel(orderId);
		return msgDTO;
	}
	
	@RequestMapping(value = "/reInitiate/{orderId}")
	public MessageDTO reInitiate(@PathVariable Long orderId) {
		MessageDTO msgDTO = orderService.reInitiate(orderId);
		return msgDTO;
	}
	
	@RequestMapping(value = "/seatIt")
	public MessageDTO seatIt(@RequestParam(value = "orderId", required = true) Long orderId, 
			@RequestParam(value="table", required=true)String tableNo) {
		MessageDTO msgDTO = orderService.seatIt(orderId, tableNo);
		return msgDTO;
	}
	
	@RequestMapping(value="/modeOfPayments",method=RequestMethod.GET)
	public List<ModeOfPayment> getMOPs()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<ModeOfPayment> mops = orderService.findMOPs();
		return mops;
	}
	
	@RequestMapping(value="/modeOfPaymentCheck/{orderId}", method=RequestMethod.GET)
	public Order modeOfPaymentsCheck(@PathVariable Long orderId)
			throws KafeneioException {
		Order order = orderService.findOrder(orderId);		
		return order;
	}
}

