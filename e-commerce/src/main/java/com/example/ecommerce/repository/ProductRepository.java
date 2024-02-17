package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByNameContaining(String name);
 
}
