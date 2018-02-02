package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.ExpenseType;

public interface ExpensesTypeRepository  extends JpaRepository<ExpenseType, Long> {

}
