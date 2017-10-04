package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.OrderStatus;

public interface OrderStatusRepository  extends JpaRepository<OrderStatus, Long> {
		OrderStatus findByCode(String code); 	
}
