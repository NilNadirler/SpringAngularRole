package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.AddProductInCartDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.service.CartItemService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600)
public class CartController {
   
	@Autowired
	private CartItemService cartService;
	
	
	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCart){
		 
		 return cartService.addProductToCart(addProductInCart);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
		 OrderDto orderDto = cartService.getCartByUserId(userId);
		 return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
}
