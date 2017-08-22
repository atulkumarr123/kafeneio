package com.kafeneio.service;

import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Order;

public interface BillingService {
	public boolean saveOrder(Order order);
}
