package com.kafeneio.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;

public interface OrderService {
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<MessageDTO> serve(Long orderId, Long mopId);
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO cancel(Long orderId, String reason);
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO reInitiate(Long orderId);
	@PreAuthorize("hasRole('ADMIN')")
	public MessageDTO seatIt(Long orderId, String tableNo);
	
	List<ModeOfPayment> findMOPs();
	Order findOrder(Long orderId);
}

