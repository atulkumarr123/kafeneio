package com.kafeneio.service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderStatus;
import com.kafeneio.repository.ModeOfPaymentRepository;
import com.kafeneio.repository.OrderRepository;
import com.kafeneio.repository.OrderStatusRepository;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService{
	
	
	@Inject
	OrderRepository orderRepository;
	
	@Inject
	OrderStatusRepository orderStatusRepository;
	

	@Inject
	ModeOfPaymentRepository modeOfPaymentRepository;

	@Override
	public MessageDTO serve(Long orderId, Long mopId){
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus servedStatus = orderStatusRepository.findByCode(ApplicationConstant.SERVED_ORDER);
			order.setStatus(servedStatus);
			ModeOfPayment modeOfPayment = modeOfPaymentRepository.findOne(mopId);
			order.setModeOfPayment(modeOfPayment);
			msgDTO.setMessage("Order "+order.getOrderNo()+" served successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
	
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}


	@Override
	public MessageDTO cancel(Long orderId) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus cancelledStatus = orderStatusRepository.findByCode(ApplicationConstant.CANCELLED_ORDER);
			order.setStatus(cancelledStatus);
			msgDTO.setMessage("Order "+order.getOrderNo()+" cancelled successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	@Override
	public MessageDTO reInitiate(Long orderId) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			OrderStatus cancelledStatus = orderStatusRepository.findByCode(ApplicationConstant.NEW_ORDER);
			order.setStatus(cancelledStatus);
			msgDTO.setMessage("Order "+order.getOrderNo()+" added successfully!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	


	@Override
	public MessageDTO seatIt(Long orderId, String table) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Order order = orderRepository.findOne(orderId);
			order.setTable(table);
			orderRepository.save(order);
			msgDTO.setMessage("Seated!");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}


	@Override
	public List<ModeOfPayment> findMOPs() {
		List<ModeOfPayment> mops = modeOfPaymentRepository.findAll();
		return mops;
	}
	
}
