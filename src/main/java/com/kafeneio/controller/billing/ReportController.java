package com.kafeneio.controller.billing;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kafeneio.exception.KafeneioException;

@Controller
public class ReportController {
		
		@RequestMapping(value = "/reports")
		public String getExpenses(ModelMap modelMap)
				throws KafeneioException, com.kafeneio.exception.BadRequestException {
			return "reports";
		}
}
