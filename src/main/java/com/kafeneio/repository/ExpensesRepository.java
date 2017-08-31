package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.Expenses;

public interface ExpensesRepository  extends JpaRepository<Expenses, Long> {

}
