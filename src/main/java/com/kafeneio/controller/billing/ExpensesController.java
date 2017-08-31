package com.kafeneio.controller.billing;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.Order;
import com.kafeneio.service.expensesService;


@RestController
public class ExpensesController {
	@Inject
	expensesService expensesService;

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
	@RequestMapping(value = "/expenses", method = RequestMethod.POST)
	public ResponseEntity<Order> generateBill(@RequestBody Order order)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		ResponseEntity<Order> response = null;
			response = new ResponseEntity<Order>(order, HttpStatus.OK);
		
			response = new ResponseEntity<Order>(order, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}
	
	
	
}
