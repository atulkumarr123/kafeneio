package com.kafeneio.service;

import java.util.List;

import com.kafeneio.model.Expenses;

public interface ExpensesService {
	
	public boolean saveExpense(List<Expenses> expenses);
}
