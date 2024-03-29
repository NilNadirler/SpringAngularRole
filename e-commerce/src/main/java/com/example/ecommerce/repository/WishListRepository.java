package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.dto.WishListDto;
import com.example.ecommerce.entities.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long>{

	
	boolean existsByUserIdAndProductId(Long userId, Long productId);

	List<WishList> findAllByUserId(Long userId);
} 

