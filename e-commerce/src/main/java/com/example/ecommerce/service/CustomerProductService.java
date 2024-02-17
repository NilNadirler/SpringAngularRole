package com.example.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repository.ProductRepository;


@Service
public class CustomerProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductDto> getAllProducts(){
		List<Product> product = productRepository.findAll();
		return product.stream().map(Product:: getDto).collect(Collectors.toList());
		
		
	}
	
	public List<ProductDto> getAllProductByName(String name){
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
				
	}
}
