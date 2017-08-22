package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> {
	
}
