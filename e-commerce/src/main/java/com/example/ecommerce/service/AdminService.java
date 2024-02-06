package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.repository.CategoryRepository;

@Service
public class AdminService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(category.getDescription());
		
		return categoryRepository.save(category);
	}

}
