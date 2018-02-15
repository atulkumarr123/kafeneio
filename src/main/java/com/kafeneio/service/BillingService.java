package com.kafeneio.service;

import java.text.ParseException;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Order;

public interface BillingService {
	
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO saveOrder(Order order, Long mopId, String date);
	public Long getOrderNo(String date) throws ParseException;

}
