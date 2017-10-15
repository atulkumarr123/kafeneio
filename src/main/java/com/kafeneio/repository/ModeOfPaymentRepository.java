package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.ModeOfPayment;

public interface ModeOfPaymentRepository  extends JpaRepository<ModeOfPayment, Long> {
	List<ModeOfPayment> findAll(); 	
}
