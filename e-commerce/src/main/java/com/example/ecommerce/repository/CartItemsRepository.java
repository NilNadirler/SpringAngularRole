package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

	Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId,long orderId, long userId);
	Optional<CartItems> findByProductIdAndUserId(Long productId, long userId);
}
