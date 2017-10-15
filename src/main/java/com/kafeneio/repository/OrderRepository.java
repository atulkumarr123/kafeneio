package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.ModeOfPayment;
import com.kafeneio.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> {
	
	@Query(value="select MAX(orderNo) from Order order where trunc(sysdate) = trunc(creationDate)")
	Long findOrderNo();
	
	@Query(value="select ord.orderNo from Order ord where trunc(sysdate) = trunc(creationDate) order by id desc")
	List<Long> findRecentOrder(); 
	
	@Query(value="select ord from Order ord where ord.status.code=:status and trunc(sysdate) = trunc(creationDate) order by id desc")
	List<Order> getOrderListToday(@Param("status")String status);

	Order findOne(Long orderid);
}
