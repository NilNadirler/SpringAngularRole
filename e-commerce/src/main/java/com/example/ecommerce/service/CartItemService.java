package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.AddProductInCartDto;
import com.example.ecommerce.dto.CartItemsDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entities.CartItems;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.repository.CartItemsRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class CartItemService {
   
	@Autowired
	private OrderRepository orderRepository; 
	
	@Autowired
	private CartItemsRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public ResponseEntity<?> addProductToCart(AddProductInCartDto cartDto){
		
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(cartDto.getUserId(),OrderStatus.Pending);
		Optional<CartItems> optionalCartItems= cartItemRepository.findByProductIdAndOrderIdAndUserId(
				cartDto.getProductId(), activeOrder.getId(), cartDto.getUserId());
		
		if(optionalCartItems.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}else {
			Optional<Product> optionalProduct = productRepository.findById(cartDto.getProductId());
			Optional<User> optionalUser = userRepository.findById(cartDto.getUserId());
			
			if(optionalProduct.isPresent() && optionalUser.isPresent()) {
				CartItems cart = new CartItems();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1L);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				 cartItemRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
				activeOrder.getCartItems().add(cart);
				
				orderRepository.save(activeOrder);
				
				
				return ResponseEntity.status(HttpStatus.CREATED).body(cart);
				
				
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
			}
		}
				
	}
	
	public OrderDto getCartByUserId(Long userId) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
		
		OrderDto orderDto = new OrderDto();
		
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setCartItems(cartItemsDtoList);
		
		return orderDto;
	}
}
