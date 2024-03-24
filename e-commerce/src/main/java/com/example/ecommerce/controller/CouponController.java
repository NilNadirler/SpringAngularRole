package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Coupon;
import com.example.ecommerce.exception.ValidationException;
import com.example.ecommerce.service.CouponService;

@RestController
@RequestMapping("/api/admin/coupons")
@CrossOrigin(maxAge = 3600)
public class CouponController {

	
	@Autowired
	private CouponService couponService;
	
	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {
		try {
			Coupon createCoupon = couponService.createCoupon(coupon);
			return ResponseEntity.ok(createCoupon);
			
		}catch(ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	
	}
	
	@GetMapping
	public ResponseEntity<List<Coupon>> getAllCoupon(){
	   return ResponseEntity.ok(couponService.getAllCoupons());
	}
	
	
}
