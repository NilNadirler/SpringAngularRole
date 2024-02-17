package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.service.CustomerProductService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600)
public class CustomerProductController {
 
	@Autowired
	private CustomerProductService customerService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> products = customerService.getAllProducts();
		
		return ResponseEntity.ok(products);
	}
	
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
		List<ProductDto> products = customerService.getAllProductByName(name);
		
		return ResponseEntity.ok(products);
	}
	
}
