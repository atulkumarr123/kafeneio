package com.kafeneio.controller.billing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kafeneio.exception.KafeneioException;


@Controller
public class ExpensesController {
	@RequestMapping(value = "/expenses")
	public String billingHome(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "expenses";
	}

}
