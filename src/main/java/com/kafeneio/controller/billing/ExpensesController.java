package com.kafeneio.controller.billing;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Order;
import com.kafeneio.service.expensesService;


@Controller
public class ExpensesController {
	
	@Inject
	expensesService expensesService;

	@RequestMapping(value = "/expenses")
	public String getExpenses(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "expenses";
	}
	
	
	/*@RequestMapping(value = "/expenses1",method=RequestMethod.POST)
	public ResponseEntity<Void> getExpenses1(@RequestBody List<Expenses> expenses)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		ResponseEntity<Void> response = null;
		boolean isSaved =expensesService.saveExpense(expenses); 
		if(isSaved){
			response = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else{
			response = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	*/
	@RequestMapping(value = "/expenses1", method = RequestMethod.POST)
	public ResponseEntity<Order> generateBill(@RequestBody Order order)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		ResponseEntity<Order> response = null;
			response = new ResponseEntity<Order>(order, HttpStatus.OK);
		
			response = new ResponseEntity<Order>(order, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}
	
	
	
}
