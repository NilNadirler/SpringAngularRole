package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(maxAge = 3600)
public class AdminController {

	

	private final CategoryService adminService;
	
	
	
	public AdminController(CategoryService adminService) {
		super();
		this.adminService = adminService;
	}


	@PostMapping("/category")
	public ResponseEntity<Category>createCategory(@RequestBody CategoryDto categoryDto){
		Category category = adminService.createCategory(categoryDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		
		return ResponseEntity.ok(adminService.getAllCaegories());
	}
}
