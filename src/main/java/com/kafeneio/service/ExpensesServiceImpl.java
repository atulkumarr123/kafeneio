package com.kafeneio.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.ExpensesDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.ExpenseType;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.RawMaterials;
import com.kafeneio.repository.ExpensesDAO;
import com.kafeneio.repository.ExpensesRepository;
import com.kafeneio.repository.ExpensesTypeRepository;

@Service
public class ExpensesServiceImpl extends BaseServiceImpl implements ExpensesService{
	
	@Inject
	ExpensesRepository expensesRepository;
	
	@Inject
	ExpensesTypeRepository expensesTypeRepository;
	
	@Inject
	ExpensesDAO expensesDAO;

	@Override
	public MessageDTO saveExpense(List<ExpensesDto> expensesDto) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			List<Expenses> expensesList = new ArrayList<Expenses>();
			expensesDto.forEach(expenseDto -> {
				Expenses expenses = new Expenses();
				expenses.setAmount(expenseDto.getAmount());
				expenses.setCreationDate(expenseDto.getCreationDate());
				expenses.setItem(expenseDto.getItem());
				expenses.setRemarks(expenseDto.getRemarks());
				ExpenseType expenseType = expensesTypeRepository.findOne(expenseDto.getExpenseTypeId());
				expenses.setExpenseType(expenseType);
				expensesList.add(expenses);	
			});
			//expensesDto.stream().forEach(expense -> expense.setCreationDate(new Date()));
			expensesRepository.save(expensesList);
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
	public MessageDTO updateExpense(ExpensesDto expensesDto) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Expenses expenses = new Expenses();
			expenses.setAmount(expensesDto.getAmount());
			expenses.setCreationDate(expensesDto.getCreationDate());
			ExpenseType expenseType = expensesTypeRepository.findOne(expensesDto.getExpenseTypeId());
			expenses.setExpenseType(expenseType);
			expenses.setId(expensesDto.getId());
			expenses.setItem(expensesDto.getItem());
			expenses.setRemarks(expensesDto.getRemarks());
			
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
	public List<ExpensesDto> fetchExpenses(Date date, Long type) {
		List<Expenses> expenses = null;
		expenses = expensesDAO.fetchExpenses(date,type);
		List<ExpensesDto> dtoList = new ArrayList<ExpensesDto>();
		expenses.forEach(expense -> {
		ExpensesDto dto = new ExpensesDto();
		dto.setAmount(expense.getAmount());
		
		dto.setCreationDate(expense.getCreationDate());
		dto.setExpenseTypeDesc(expense.getExpenseType().getDescription());
		dto.setId(expense.getId());
		dto.setItem(expense.getItem());
		dto.setExpenseTypeId(expense.getExpenseType().getId());
		dto.setExpenseTypeDesc(expense.getExpenseType().getDescription());
		dto.setRemarks(expense.getRemarks());
		dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public List<ExpenseType> findExpenseType() {
		List<ExpenseType> expensesType = expensesTypeRepository.findAll();
		return expensesType;
	}
}
