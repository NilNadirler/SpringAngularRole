package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<Order,Long> {
	
	  Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

}
