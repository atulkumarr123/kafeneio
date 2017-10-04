package com.kafeneio.service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Expenses;
import com.kafeneio.repository.ExpensesRepository;

@Service
public class ExpensesServiceImpl extends BaseServiceImpl implements ExpensesService{
	@Inject
	ExpensesRepository expensesRepository;

	@Override
	public MessageDTO saveExpense(List<Expenses> expenses) {
		MessageDTO msgDTO = new MessageDTO();
		try{
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
}
