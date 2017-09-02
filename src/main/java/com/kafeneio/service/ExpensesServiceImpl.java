package com.kafeneio.service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kafeneio.model.Expenses;
import com.kafeneio.repository.ExpensesRepository;

@Service
public class ExpensesServiceImpl extends BaseServiceImpl implements ExpensesService{
	@Inject
	ExpensesRepository expensesRepository;
//	@Override
	public boolean saveExpense(List<Expenses> expenses) {
			boolean isSaved;
			try{
				expensesRepository.save(expenses);
				isSaved = true;
			}
			catch(Exception exception){
				isSaved = false;
			}
			return isSaved;
		}
}
