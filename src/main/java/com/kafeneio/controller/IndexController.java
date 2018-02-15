package com.kafeneio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.service.FoodService;
import com.kafeneio.service.IndexService;
import com.kafeneio.utils.AppUtils;

@Controller
@RequestMapping
public class IndexController {

	
	@Autowired
	private Environment environment;	
	
	@Inject
	FoodService foodService;
	@Inject
	IndexService indexService;
	
	
	public static Map<String,String> configParameters = null;
	
	  @RequestMapping(value="/home", method = RequestMethod.GET)
	  public String getHomePage(ModelMap modelMap) {
		  homePage(modelMap);
		  return "index";
	  }

	  private void homePage(ModelMap modelMap) {
		  modelMap.put("currentDateTime", new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT).format(new Date()));
		  modelMap.put("currentDate", new SimpleDateFormat(ApplicationConstant.DATE_FORMAT).format(new Date()));
		  modelMap.put("yesterdayDate", AppUtils.getYesterday());
		  modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);

		  AppUtils.getYesterday();
	  }
	  
	  @RequestMapping(value="/", method = RequestMethod.GET)
	  public String getIndexPage(ModelMap modelMap) {
		  if(configParameters == null){
//			  configParameters = indexService.getConfigParameters();
		  }
		  homePage(modelMap);
		  return "index";
	  }

	  @PreAuthorize("hasRole('ADMIN')")
	  @RequestMapping(value="/restraMenu", method = RequestMethod.GET)
	  public String getMenu(ModelMap modelMap) {
		  List<FoodCategory> foodCategoryList = foodService.findFoodCategory();
		  modelMap.put("foodCategoryList",foodCategoryList);
		  DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  modelMap.put("currentDateTime", format.format(new Date()));
		  modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);

//		  modelMap.putAll(configParameters);
		  return "restraMenu";
	  }
	  
}

@RestController
class IndexRestController{

	public static List<FoodCategory> foodCategoryList = null;
	
	@Inject
	FoodService foodService;
	
	
	
	  @PreAuthorize("hasRole('ADMIN')")
	  @RequestMapping(value="/loadRestraMenu", method = RequestMethod.GET)
	  public  Map<String,Set<FoodItems>> loadRestraMenu() {
		 
		  if(foodCategoryList == null){
		  foodCategoryList = foodService.findFoodCategory();
		  }
		  Map<String,Set<FoodItems>> wholeMenu = new HashMap<String, Set<FoodItems>>();
		  foodCategoryList.forEach(category->{
			  wholeMenu.put(category.getFoodCode(), category.getFoodItems());
		  });
		  return wholeMenu;
	  }
	
}
