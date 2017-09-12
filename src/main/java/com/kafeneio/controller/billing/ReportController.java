package com.kafeneio.controller.billing;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Expenses;
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
	@RequestMapping(value = "/expenseList")
	public List<Expenses> fetchExpenses(@RequestParam(value = "fromDate", required = true) String fromDate, 
			@RequestParam(value="toDate", required=true)String toDate) {
		List<Expenses> expenses = reportService.fetchExpenses(fromDate, toDate); 
		return expenses;
	}
}

@Controller
class ReportLoaderController{

	@RequestMapping(value = "/reports")
	public String reportHome(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		//modelMap.put("dateTimeFormat", ApplicationConstant.DATE_TIME_FORMAT);
		modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);
		return "reports";
	}
	
}

