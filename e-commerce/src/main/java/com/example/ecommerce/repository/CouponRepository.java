package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long>{
	
	boolean existsByCode(String code);

	Optional<Coupon> findByCode(String code);

}
