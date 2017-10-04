package com.kafeneio.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.DTO.MessageDTO;

public interface OrderService {
	
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO serve(Long orderId);
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO cancel(Long orderId);
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO seatIt(Long orderId, String tableNo);
}
