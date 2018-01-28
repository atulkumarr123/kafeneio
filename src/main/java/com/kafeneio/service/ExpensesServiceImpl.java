package com.kafeneio.service;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Expenses;
import com.kafeneio.repository.ExpensesDAO;
import com.kafeneio.repository.ExpensesRepository;

@Service
public class ExpensesServiceImpl extends BaseServiceImpl implements ExpensesService{
	
	@Inject
	ExpensesRepository expensesRepository;
	
	@Inject
	ExpensesDAO expensesDAO;

	@Override
	public MessageDTO saveExpense(List<Expenses> expenses) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			expenses.stream().forEach(expense -> expense.setCreationDate(new Date()));
			expensesRepository.save(expenses);
			msgDTO.setMessage("Expenses Saved");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}

	@Override
	public MessageDTO updateExpense(Expenses expenses) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			expensesRepository.save(expenses);
			msgDTO.setMessage("Expenses Updated");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
		
	}

	@Override
	public MessageDTO deleteExpense(Long id) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			expensesRepository.delete(id);
			msgDTO.setMessage("Expenses deleted");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured During Deletion");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}

	@Override
	public List<Expenses> fetchExpenses() {
		List<Expenses> expenses = null;
		expenses = expensesDAO.fetchExpenses();
		return expenses;
	}
}
