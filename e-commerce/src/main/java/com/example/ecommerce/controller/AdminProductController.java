package com.example.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.FAQDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.service.AdminProductService;
import com.example.ecommerce.service.FAQService;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(maxAge = 3600)
public class AdminProductController {


	private final AdminProductService productService;
	private final FAQService faqService;
	
	
	
	
	public AdminProductController(AdminProductService productService, FAQService faqService) {
		super();
		this.productService = productService;
		this.faqService = faqService;
	}

	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException{
		ProductDto productDto1 = productService.addProduct(productDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> products = productService.getAllProducts();
		
		return ResponseEntity.ok(products);
	}
	
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
		List<ProductDto> products = productService.getAllProductByName(name);
		
		return ResponseEntity.ok(products);
	}
	
	@PostMapping("/faq/{productId}")
	public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId,@RequestBody FAQDto faqDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
		
	}
	
	
	
	
}
