package com.kafeneio.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Order;

public interface BillingService {
	
	@PreAuthorize("hasRole('ADMIN')")
	public boolean saveOrder(Order order);
	public Long getOrderNo();

}
