package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.ExpenseType;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.ExpensesDto;

public interface ExpensesService {
	
	public MessageDTO saveExpense(List<ExpensesDto> expenses);
	public MessageDTO updateExpense(Expenses expenses);
	public MessageDTO deleteExpense(Long id);
	public List<Expenses> fetchExpenses();
	public List<ExpenseType> findExpenseType();
	
}
