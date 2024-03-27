package com.example.ecommerce.dto;

import java.util.List;

public class ProductDetailDto {

	private ProductDto productDto;
	
	private List<ReviewDto> reviewDtoList;
	
	private List<FAQDto> faqdtoList;

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	public List<ReviewDto> getReviewDtoList() {
		return reviewDtoList;
	}

	public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
		this.reviewDtoList = reviewDtoList;
	}

	public List<FAQDto> getFaqdtoList() {
		return faqdtoList;
	}

	public void setFaqdtoList(List<FAQDto> faqdtoList) {
		this.faqdtoList = faqdtoList;
	}


	
	
	
			
}
