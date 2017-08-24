package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> {
	
	@Query(value="select MAX(orderNo) from Order")
	Long findOrderNo();
	
}
