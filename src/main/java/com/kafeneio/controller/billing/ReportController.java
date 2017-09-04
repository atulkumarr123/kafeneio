package com.kafeneio.controller.billing;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Order;
import com.kafeneio.service.ReportService;

@RestController
public class ReportController {


	@Inject
	ReportService reportService;
	@RequestMapping(value = "/orderList")
	public List<Order> fetchOrders(@RequestParam(value = "fromDate", required = true) String fromDate, 
			@RequestParam(value="toDate", required=true)String toDate) {
		List<Order> orders = reportService.fetchOrders(fromDate, toDate); 
		return orders;
	}
}

@Controller
class ReportLoaderController{

	@RequestMapping(value = "/reports")
	public String reportHome()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "reports";
	}
	
}

