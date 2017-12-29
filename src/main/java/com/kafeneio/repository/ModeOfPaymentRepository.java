package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.ModeOfPayment;

public interface ModeOfPaymentRepository  extends JpaRepository<ModeOfPayment, Long> {
	List<ModeOfPayment> findAll(); 	
	


	@Query(value="select modeOfPayment  from ModeOfPayment modeOfPayment")
	List<ModeOfPayment> findModeOfPayment();	
	
}
