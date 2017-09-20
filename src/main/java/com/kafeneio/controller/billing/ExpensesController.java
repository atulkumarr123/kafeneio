package com.kafeneio.controller.billing;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Expenses;
import com.kafeneio.service.ExpensesService;



@RestController
public class ExpensesController {
	
	@Inject
	ExpensesService expensesService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/expenses", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> saveExpenses(@RequestBody List<Expenses> expenses)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO successDTO=new MessageDTO();
		ResponseEntity<MessageDTO> response = null;
		boolean isSaved =expensesService.saveExpense(expenses);
		if(isSaved){
			successDTO.setMessage("Expenses Saved");
			successDTO.setStatusCode(200);
			response = new ResponseEntity<MessageDTO>(successDTO,HttpStatus.OK);
		}
		else{
			successDTO.setMessage("Some Error Occured");
			successDTO.setStatusCode(500);
			response = new ResponseEntity<MessageDTO>(successDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}


@Controller
class ExpenseLoaderController{

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/expenses")
	public String getExpenses(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		return "expenses";
	}
	
}
