package com.kafeneio.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;
import com.kafeneio.model.OrderDetails;
import com.kafeneio.repository.OrderRepository;

@Service
public class BillingServiceImpl extends BaseServiceImpl implements BillingService{
	
	
	@Inject
	OrderRepository orderRepository;

	@Override
	public boolean saveOrder(Order order){
		boolean isSaved;
		try{
			populateOrder(order);
			orderRepository.save(order);
			isSaved = true;
		}
		catch(Exception exception){
			isSaved = false;
		}
		return isSaved;
	}

	private void populateOrder(Order order) {
		Long orderNo = orderRepository.findOrderNo();
		orderNo=(orderNo!=null)?(orderNo+1):1;
		double amount = 0;
		Iterator<OrderDetails> itr = order.getOrderDetails().iterator();	
		while(itr.hasNext()){
			OrderDetails orderDetails= itr.next();
			amount=amount+orderDetails.getAmount();
		}
		
		order.setOrderNo(orderNo);
		order.setCreation_date(new Date());
		order.setAmount(amount);
	}

}
