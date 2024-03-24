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
import com.example.ecommerce.dto.PlaceOrderDto;
import com.example.ecommerce.service.CartItemService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600)
public class CartController {
   

	private CartItemService cartService;
	
	
	public CartController(CartItemService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCart){
		 
		 return cartService.addProductToCart(addProductInCart);
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
		 OrderDto orderDto = cartService.getCartByUserId(userId);
		 return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	@GetMapping("/applyCoupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable Long userId,@PathVariable String code ){
		
		OrderDto dto = cartService.applyCoupon(userId,code);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
		}
	
	@PostMapping("/increaseQuantity")
	public ResponseEntity<?> increaseQuantity(@RequestBody AddProductInCartDto addProductInCart){
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseQuantity(addProductInCart));
	}
	
	@PostMapping("/decreaseQuantity")
	public ResponseEntity<?> decreaseQuantity(@RequestBody AddProductInCartDto addProductInCart){
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.deacreaseQuantity(addProductInCart));
	}
	
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto){
    	return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
    }
	
	
	
}
