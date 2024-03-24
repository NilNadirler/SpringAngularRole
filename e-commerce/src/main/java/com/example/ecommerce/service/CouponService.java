package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Coupon;
import com.example.ecommerce.exception.ValidationException;
import com.example.ecommerce.repository.CouponRepository;

@Service
public class CouponService {
   
	@Autowired
	private CouponRepository couponRepository;
	
	public Coupon createCoupon(Coupon coupon) {
		if(couponRepository.existsByCode(coupon.getCode())) {
			throw new ValidationException("Coupon code already exist");
		}
		
		return couponRepository.save(coupon);
	}
	
	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
}
