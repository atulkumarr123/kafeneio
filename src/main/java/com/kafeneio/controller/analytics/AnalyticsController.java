package com.kafeneio.controller.analytics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.service.AnalyticsService;
import com.kafeneio.service.FoodService;
import com.kafeneio.service.ReportService;


@RestController
public class AnalyticsController {


	@Inject
	AnalyticsService analyticsService;
	
	@RequestMapping(value = "/saleAnalysis")
	public List<OrderDetails> fetchAnalysis(@RequestParam(value = "fromDate", required = true) String fromDate, 
			@RequestParam(value="toDate", required=true)String toDate,
			@RequestParam(value="modes",required= false)String modes,
			@RequestParam(value="category")String category,
			@RequestParam(value="foodItems", required=false)String foodItems){
		List<OrderDetails> orderDetails = analyticsService.fetchOrders(fromDate, toDate, modes, category,foodItems); 
		return orderDetails;
	}

}


@Controller
class AnalyticsLoaderController{
	@Inject
	ReportService reportService;	
	@Inject
	FoodService foodService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/salesAnalytics")
	public String salesAnalytics(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		modelMap.put("dateTimeFormat", ApplicationConstant.DATE_TIME_FORMAT);
		modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);
		//List<ModeOfPayment> modeOfPayments = new ArrayList<ModeOfPayment>();
		List<FoodCategory> foodList = foodService.findFoodCategory();
		modelMap.put("categoryList",foodList);
		FoodCategory foodCategory = new FoodCategory();
		modelMap.put("foodCategoryReport", foodCategory);	
		List<ModeOfPayment> modes  = reportService.findModeOfPayment();
		modelMap.put("modeOfPayments",modes);
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_FORMAT);
		String fromDate = dateFormat.format(new Date());
		String toDate = getTomorrow(dateFormat);
		fromDate = fromDate + " 12:00 AM";
		toDate = toDate + " 12:00 AM";
		modelMap.put("fromDateTime", fromDate);
		modelMap.put("toDateTime", toDate);
		String url = "/analytics/salesAnalytics"; 
		return url;
	}
	private String getTomorrow(SimpleDateFormat dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1); // to get previous year add -1
		Date nextDay = cal.getTime();
		String tomorrow = dateFormat.format(nextDay);
		return tomorrow;
	}
}