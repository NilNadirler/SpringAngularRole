package com.example.ecommerce.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.repository.OrderRepository;

@Service
public class AdminOrderService {

	private final OrderRepository orderRepository;

	public AdminOrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
		
	}
	
	public List<OrderDto> getAllPlacedOrder(){
		
		List<Order> orderList = orderRepository.findByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));
		
		return orderList.stream().sorted((Comparator.comparing(Order::getId))).map(Order::orderDto).collect(Collectors.toList());
		
	}
	
	public OrderDto changeOrderStatus(Long orderId,String status) {
		Optional<Order>optionalOrder =orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			if(Objects.equals(status, "Shipped")) {
				order.setOrderStatus(OrderStatus.Shipped);
			}else if(Objects.equals(status, "Delivered")) {
				order.setOrderStatus(OrderStatus.Delivered);
			}
			
			return orderRepository.save(order).orderDto();
		}
		
		return null;
	}
	
	public List<OrderDto> getMyPlaceOrders(Long userId){
		return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed,OrderStatus.Shipped,
				OrderStatus.Delivered)).stream().sorted((Comparator.comparing(Order::getId))).map(Order::orderDto).collect(Collectors.toList());
	}
	
}
