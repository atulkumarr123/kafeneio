package com.kafeneio.controller.billing;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.ExpensesDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.ExpenseType;
import com.kafeneio.model.Expenses;
import com.kafeneio.service.ExpensesService;

@RestController
public class ExpensesController {
	
	@Inject
	ExpensesService expensesService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/expenses", method = RequestMethod.POST)
	public MessageDTO saveExpenses(@RequestBody List<ExpensesDto> expenses)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = expensesService.saveExpense(expenses);
		return msgDTO;
	}
	
	@RequestMapping(value = "/expensesList")
	public List<ExpensesDto> fetchExpenses() {
		List<ExpensesDto> expenses = expensesService.fetchExpenses(); 
		return expenses;
	}
	
	@RequestMapping(value = "/deleteExpense/{id}")
	public MessageDTO getOrderListToday(@PathVariable Long id) {
		MessageDTO msgDTO = expensesService.deleteExpense(id); 
		return msgDTO ;
	}

}


@Controller
class ExpenseLoaderController{

	@Inject
	ExpensesService expenseService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/expenses")
	public String getExpenses(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<ExpenseType> expenseTypeList = expenseService.findExpenseType();
		  modelMap.put("expenseTypeList",expenseTypeList);
			ExpenseType expenseType = new ExpenseType();
			modelMap.put("expenseType", expenseType);
		  
		modelMap.put("currentDate", new SimpleDateFormat(ApplicationConstant.DATE_FORMAT).format(new Date()));
		return "expenses";
	}
	
}
