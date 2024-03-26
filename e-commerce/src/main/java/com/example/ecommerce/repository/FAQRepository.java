package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.FAQ;

public interface FAQRepository extends JpaRepository<FAQ,Long> {

}
