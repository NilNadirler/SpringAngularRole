package com.example.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.AddProductInCartDto;
import com.example.ecommerce.dto.CartItemsDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.dto.PlaceOrderDto;
import com.example.ecommerce.entities.CartItems;
import com.example.ecommerce.entities.Coupon;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.exception.ValidationException;
import com.example.ecommerce.repository.CartItemsRepository;
import com.example.ecommerce.repository.CouponRepository;
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
	
	@Autowired
	private CouponRepository couponRepository;
	
	
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
				
				 CartItems cart2 = cartItemRepository.save(cart);
				
				activeOrder.setTotalAmount((activeOrder.getTotalAmount()==null ? 0:activeOrder.getTotalAmount()) +cart.getPrice());
				activeOrder.setAmount((activeOrder.getAmount()==null? 0:activeOrder.getAmount()) +cart.getPrice());
				activeOrder.getCartItems().add(cart2);
				
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
		if(activeOrder.getCoupon() !=null) {
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
		
		return orderDto;
	}

	public OrderDto applyCoupon(Long userId, String code) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		Coupon coupon = couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("There is no such a code"));
		if(couponIsExpired(coupon)) {
			throw new ValidationException("Coupon is expired");
		}
		
		double discountAmount = (coupon.getDiscount()*activeOrder.getTotalAmount())/100;
		double netAmount = activeOrder.getTotalAmount()-discountAmount;
		
		activeOrder.setDiscount((long)discountAmount);
		activeOrder.setAmount((long)netAmount);
		activeOrder.setCoupon(coupon);
		
		orderRepository.save(activeOrder);
		
		
		return activeOrder.orderDto();
	}


	public OrderDto increaseQuantity(AddProductInCartDto addProductInCart) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCart.getUserId(), OrderStatus.Pending);
		Optional <CartItems> optionalCartItem= cartItemRepository.findByProductIdAndOrderIdAndUserId(addProductInCart.getProductId(), activeOrder.getId(), addProductInCart.getUserId());
		Optional<Product> optionalProduct = productRepository.findById(addProductInCart.getProductId());
		
		if(optionalCartItem.isPresent() && optionalProduct.isPresent()) {
			CartItems cartItem = optionalCartItem.get();
			Product product = optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount()+product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount()+product.getPrice());
			
			cartItem.setQuantity(cartItem.getQuantity()+1);
			if(activeOrder.getCoupon()!=null) {
				double discountAmount = (activeOrder.getCoupon().getDiscount()*activeOrder.getTotalAmount())/100;
				double netAmount = activeOrder.getTotalAmount()-discountAmount;
				
				activeOrder.setDiscount((long)discountAmount);
				activeOrder.setAmount((long)netAmount);
				
			}
			
			cartItemRepository.save(cartItem);
			orderRepository.save(activeOrder);
			return activeOrder.orderDto();
		}
		
		return null;
		
		
	}
	

	public OrderDto deacreaseQuantity(AddProductInCartDto addProductInCart) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCart.getUserId(), OrderStatus.Pending);
		Optional <CartItems> optionalCartItem= cartItemRepository.findByProductIdAndOrderIdAndUserId(addProductInCart.getProductId(), activeOrder.getId(), addProductInCart.getUserId());
		Optional<Product> optionalProduct = productRepository.findById(addProductInCart.getProductId());
		
		if(optionalCartItem.isPresent() && optionalProduct.isPresent()) {
			CartItems cartItem = optionalCartItem.get();
			Product product = optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount()-product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount()-product.getPrice());
			
			cartItem.setQuantity(cartItem.getQuantity()-1);
			if(activeOrder.getCoupon()!=null) {
				double discountAmount = (activeOrder.getCoupon().getDiscount()*activeOrder.getTotalAmount())/100;
				double netAmount = activeOrder.getTotalAmount()-discountAmount;
				
				activeOrder.setDiscount((long)discountAmount);
				activeOrder.setAmount((long)netAmount);
				
			}
			
			cartItemRepository.save(cartItem);
			orderRepository.save(activeOrder);
			return activeOrder.orderDto();
			
		}
		
		return null;
		
	}
	
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
		Optional<User> optionalUser= userRepository.findById(placeOrderDto.getUserId());
		if(optionalUser.isPresent()) {
			activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
			activeOrder.setAddress(placeOrderDto.getAddress());
			activeOrder.setDate(new Date());
			activeOrder.setOrderStatus(OrderStatus.Placed);
			activeOrder.setTrackingId(UUID.randomUUID());
			
			orderRepository.save(activeOrder);
			
			Order order = new Order();
			order.setAmount(0L);
			order.setTotalAmount(0L);
			order.setDiscount(0L);
			order.setUser(optionalUser.get());
			order.setOrderStatus(OrderStatus.Pending);
			orderRepository.save(order);
			
			return activeOrder.orderDto();
		}
		return null;
	}
	
	
	private boolean couponIsExpired(Coupon coupon) {
		 Date date = new Date();
		 Date expireDate = coupon.getExpirationDate();
		 
		 return expireDate != null && date.after(expireDate);
		 
	}
	
	
}
