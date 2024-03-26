package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.service.AdminOrderService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(maxAge = 3600)
public class AdminOrderController {
	
	private final AdminOrderService adminOrderService;

	public AdminOrderController(AdminOrderService adminOrderService) {
		super();
		this.adminOrderService = adminOrderService;
	}
	
	@GetMapping("/placedOrders")
	public ResponseEntity <List<OrderDto>> getAllPlacedOrders(){
		
		return ResponseEntity.ok(adminOrderService.getAllPlacedOrder());
	}
	
	@GetMapping("/order/{orderId}/{status}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId,@PathVariable String status){
		OrderDto orderDto = adminOrderService.changeOrderStatus(orderId, status);
		if(orderDto==null)
			return new ResponseEntity<>("Somethin went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity <List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId){
		
		return ResponseEntity.ok(adminOrderService.getMyPlaceOrders(userId));
	}
	

}
