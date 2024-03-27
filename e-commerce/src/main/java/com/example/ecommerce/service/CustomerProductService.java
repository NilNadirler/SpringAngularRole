package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.ProductDetailDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entities.FAQ;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.Review;
import com.example.ecommerce.repository.FAQRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ReviewRepository;


@Service
public class CustomerProductService {

	private ProductRepository productRepository;
	private FAQRepository faqRepository;
	private ReviewRepository reviewRepository;
	
	

	
	public CustomerProductService(ProductRepository productRepository, FAQRepository faqRepository,
			ReviewRepository reviewRepository) {
		super();
		this.productRepository = productRepository;
		this.faqRepository = faqRepository;
		this.reviewRepository = reviewRepository;
	}

	public List<ProductDto> getAllProducts(){
		List<Product> product = productRepository.findAll();
		return product.stream().map(Product:: getDto).collect(Collectors.toList());
		
		
	}
	
	public List<ProductDto> getAllProductByName(String name){
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
				
	}
	
	public ProductDetailDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			List<FAQ> faqList = faqRepository.findAllByProductId(productId);
			List<Review> reviewList = reviewRepository.findAllByProductId(productId);
			
			ProductDetailDto productDetailDto = new ProductDetailDto();
			
				productDetailDto.setProductDto(optionalProduct.get().getDto());
				productDetailDto.setFaqdtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
				productDetailDto.setReviewDtoList(reviewList.stream().map(Review :: getDto).collect(Collectors.toList()));
				
				return productDetailDto;		
			
		}
		
		return null;
	}
}
