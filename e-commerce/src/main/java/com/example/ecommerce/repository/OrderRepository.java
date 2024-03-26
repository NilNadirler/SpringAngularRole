package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<Order,Long> {
	
	  Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
	  
	  List<Order> findByOrderStatusIn(List<OrderStatus> orderStatusList);
	  
	  List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);

}
