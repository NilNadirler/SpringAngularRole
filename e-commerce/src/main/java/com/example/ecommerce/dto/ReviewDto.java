package com.example.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;


public class ReviewDto {

private Long id;
	
	private String description;
	
	private Long rating;
	
	private byte[] returnImg;
	

	private Long userId;
	
	private Long productId;
	
	private MultipartFile img;
	
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public byte[] getReturnImg() {
		return returnImg;
	}

	public void setReturnImg(byte[] returnImg) {
		this.returnImg = returnImg;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
