package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.FAQ;
import com.example.ecommerce.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findAllByProductId(Long ProductId);
}
