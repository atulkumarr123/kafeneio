package com.kafeneio.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kafeneio.constants.ApplicationConstant;

@Controller
@RequestMapping
public class IndexController {

	  @RequestMapping(value="/", method = RequestMethod.GET)
	  public String getIndexPage(ModelMap modelMap) {
		  DateFormat format = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		  modelMap.put("currentDateTime", format.format(new Date()));
		  return "index";
	  }
	  
}
