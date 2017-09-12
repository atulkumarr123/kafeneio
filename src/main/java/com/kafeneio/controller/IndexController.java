package com.kafeneio.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kafeneio.constants.ApplicationConstant;

@Controller
@RequestMapping
public class IndexController {

	@Autowired
	private Environment environment;	
	
	  @RequestMapping(value="/", method = RequestMethod.GET)
	  public String getIndexPage(ModelMap modelMap) {
		  DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  //modelMap.put("currentDateTime", ApplicationConstant.DATE_TIME_FORMAT);
		  System.out.println(environment.getProperty("kafeneio.main.title"));
		  return "index";
	  }
}
