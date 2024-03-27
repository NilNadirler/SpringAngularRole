package com.example.ecommerce.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.OrderProductResponseDto;
import com.example.ecommerce.dto.ReviewDto;
import com.example.ecommerce.service.ReviewService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600)
public class ReviewController {
	
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	
	@GetMapping("/ordered-product/{orderId}")
	public ResponseEntity<OrderProductResponseDto>getOrderedProducsDetailsByOrderId(@PathVariable Long orderId){
		
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderDto(orderId));
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException{
		ReviewDto reviewDto1= reviewService.giveReview(reviewDto);
	if(reviewDto1==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
	}

}
