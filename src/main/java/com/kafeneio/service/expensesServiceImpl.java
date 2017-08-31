package com.kafeneio.service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kafeneio.model.Expenses;
import com.kafeneio.repository.ExpensesRepository;

@Service
public class expensesServiceImpl extends BaseServiceImpl implements expensesService{
	@Inject
	ExpensesRepository expensesRepository;
//	@Override
	public boolean saveExpense(Expenses expenses) {
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
