package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Expenses;

public interface ExpensesService {
	
	public MessageDTO saveExpense(List<Expenses> expenses);
	public MessageDTO updateExpense(Expenses expenses);
	public MessageDTO deleteExpense(Long id);
	public List<Expenses> fetchExpenses();
	
}
