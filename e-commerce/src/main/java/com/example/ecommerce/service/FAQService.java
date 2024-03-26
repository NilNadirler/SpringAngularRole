package com.example.ecommerce.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.FAQDto;
import com.example.ecommerce.entities.FAQ;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repository.FAQRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class FAQService {

	private final FAQRepository faqRepository;
	
	private final ProductRepository productRepository;

	public FAQService(FAQRepository faqRepository, ProductRepository productRepository) {
		super();
		this.faqRepository = faqRepository;
		this.productRepository = productRepository;
	}
	
	public FAQDto postFAQ(Long producId, FAQDto faqDto) {
		Optional<Product> optionalProduct= productRepository.findById(producId);
		if(optionalProduct.isPresent()) {
			FAQ faq = new FAQ();
			
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFAQDto();
		}
		
		return null;
		
	}
	
}
