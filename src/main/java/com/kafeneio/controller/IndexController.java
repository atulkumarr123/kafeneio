package com.kafeneio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.service.FoodService;

@Controller
@RequestMapping
public class IndexController {

	
	@Autowired
	private Environment environment;	
	
	@Inject
	FoodService foodService;
	
	  @RequestMapping(value="/home", method = RequestMethod.GET)
	  public String getHomePage(ModelMap modelMap) {
		 /* DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  //modelMap.put("currentDateTime", ApplicationConstant.DATE_TIME_FORMAT);
		  System.out.println(environment.getProperty("kafeneio.main.title"));*/
		  modelMap.put("currentDate", new SimpleDateFormat(ApplicationConstant.DATE_FORMAT).format(new Date()));
		  return "index";
	  }
	  
	  @RequestMapping(value="/", method = RequestMethod.GET)
	  public String getIndexPage(ModelMap modelMap) {
		 /* DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  //modelMap.put("currentDateTime", ApplicationConstant.DATE_TIME_FORMAT);
		  System.out.println(environment.getProperty("kafeneio.main.title"));*/
		  modelMap.put("currentDate", new SimpleDateFormat(ApplicationConstant.DATE_FORMAT).format(new Date()));
		  return "index";
	  }

	  @PreAuthorize("hasRole('ADMIN')")
	  @RequestMapping(value="/restraMenu", method = RequestMethod.GET)
	  public String getMenu(ModelMap modelMap) {
		  List<FoodCategory> foodCategoryList = foodService.findFoodCategory();
//		  modelMap.put("foodCategoryList",foodCategoryList);
		  DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  System.out.println(environment.getProperty("kafeneio.main.title"));
		  return "restraMenu";
	  }
	  
	  
}

class IndexRestController{

	@Inject
	FoodService foodService;
	
	  @PreAuthorize("hasRole('ADMIN')")
	  @RequestMapping(value="/loadRestraMenu", method = RequestMethod.GET)
	  public String getMenu(ModelMap modelMap) {
		  List<FoodCategory> foodCategoryList = foodService.findFoodCategory();
		  modelMap.put("foodCategoryList",foodCategoryList);
		  DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  return "restraMenu";
	  }
	
}
