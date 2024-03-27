package com.example.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.OrderProductResponseDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ReviewDto;
import com.example.ecommerce.entities.CartItems;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.Review;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ReviewRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class ReviewService {

	 private final OrderRepository orderRepository;
	 private final UserRepository userRepository;
	 private final ProductRepository productRepository;
	 private final ReviewRepository reviewRepository;

	public ReviewService(OrderRepository orderRepository, UserRepository userRepository,
			ProductRepository productRepository, ReviewRepository reviewRepository) {
		super();
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
	}

	public OrderProductResponseDto getOrderedProductsDetailsByOrderDto(Long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		OrderProductResponseDto responseDto = new OrderProductResponseDto();
		if(optionalOrder.isPresent()) {
			responseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList=new ArrayList<>();
			
			for(CartItems cartItems: optionalOrder.get().getCartItems()) {
				ProductDto productDto = new ProductDto();
				
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				productDto.setByteImg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			responseDto.setProductDtoList(productDtoList);
		}
		return responseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct= productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Review review = new Review();
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			
			
			return reviewRepository.save(review).getDto();
		}
		
		  return null;
	}
   
	 
}
