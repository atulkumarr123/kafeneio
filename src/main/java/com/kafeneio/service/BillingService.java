package com.kafeneio.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Order;

public interface BillingService {
	
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO saveOrder(Order order);
	public Long getOrderNo();

}
