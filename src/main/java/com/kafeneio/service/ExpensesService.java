package com.kafeneio.service;

import java.util.Date;
import java.util.List;

import com.kafeneio.DTO.ExpensesDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.ExpenseType;

public interface ExpensesService {
	
	public MessageDTO saveExpense(List<ExpensesDto> expenses);
	public MessageDTO updateExpense(ExpensesDto expensesDto);
	public MessageDTO deleteExpense(Long id);
	public List<ExpensesDto> fetchExpenses(Date date, Long type );
	public List<ExpenseType> findExpenseType();
	
}
