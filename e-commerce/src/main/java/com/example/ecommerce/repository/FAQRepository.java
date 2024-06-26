package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.FAQ;

public interface FAQRepository extends JpaRepository<FAQ,Long> {

	List<FAQ> findAllByProductId(Long ProductId);
}
