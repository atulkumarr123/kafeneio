package com.kafeneio.controller.billing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.service.ExpensesService;
import com.kafeneio.service.ReportService;

@RestController
@RequestMapping(value = "/report")
public class ReportController {


	@Inject
	ReportService reportService;
	
	@Inject
	ExpensesService expensesService;
	
	@RequestMapping(value = "/orderList")
	public List<Order> fetchOrders(@RequestParam(value = "fromDate", required = true) String fromDate, 
			@RequestParam(value="toDate", required=true)String toDate,
			@RequestParam(value="modes")String modes) {
		List<Order> orders = reportService.fetchOrders(fromDate, toDate, modes); 
		return orders;
	}
	
	@RequestMapping(value = "/expenseList")
	public List<Expenses> fetchExpenses(@RequestParam(value = "fromDate", required = true) String fromDate, 
			@RequestParam(value="toDate", required=true)String toDate) {
		List<Expenses> expenses = reportService.fetchExpenses(fromDate, toDate); 
		return expenses;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/getOrderListToday/{status}")
	public List<Order> getOrderListToday(@PathVariable String status) {
		List<Order> orders = reportService.getOrderListToday(status); 
		return orders;
	}
	
	@RequestMapping(value = "/updateExpenses", method = RequestMethod.POST)
	public MessageDTO updateExpenses(@RequestBody Expenses expenses)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = expensesService.updateExpense(expenses);
		return msgDTO;
	}
}

@Controller
class ReportLoaderController{

	@Inject
	ReportService reportService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/reports")
	public String reportHome(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		//modelMap.put("dateTimeFormat", ApplicationConstant.DATE_TIME_FORMAT);
		modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);
		//List<ModeOfPayment> modeOfPayments = new ArrayList<ModeOfPayment>();
		
		List<ModeOfPayment> modes  = reportService.findModeOfPayment();
		
	  /*ModeOfPayment mode  = new ModeOfPayment();
		mode.setCode("rt");
		mode.setDescription("rt");
		mode.setId(3l); 
		
		modeOfPayments.add(modes); */
		
		modelMap.put("modeOfPayments",modes);
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_FORMAT);
		String fromDate = dateFormat.format(new Date());
		String toDate = getTomorrow(dateFormat);
		fromDate = fromDate + " 12:00 AM";
		toDate = toDate + " 12:00 AM";
		modelMap.put("fromDateTime", fromDate);
		modelMap.put("toDateTime", toDate);
		return "reports";
	}

	private String getTomorrow(SimpleDateFormat dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1); // to get previous year add -1
		Date nextDay = cal.getTime();
		String tomorrow = dateFormat.format(nextDay);
		return tomorrow;
	}
	
}

