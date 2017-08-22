package com.kafeneio.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;
import com.kafeneio.repository.FoodRepository;
import com.kafeneio.repository.OrderRepository;

@Service
public class BillingServiceImpl extends BaseServiceImpl implements BillingService{
	
	
	@Inject
	OrderRepository orderRepository;

	@Override
	public boolean saveOrder(Order order){
		boolean isSaved;
		try{
			orderRepository.save(order);
			isSaved = true;
		}
		catch(Exception exception){
			isSaved = true;
		}
		return isSaved;
	}
	
	
	
}
