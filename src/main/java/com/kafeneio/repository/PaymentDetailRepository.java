package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.PaymentModel;

public interface PaymentDetailRepository extends JpaRepository<PaymentModel, Long> {
	List<PaymentModel> findByStatus(Boolean status);

}
