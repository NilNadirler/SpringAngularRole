package com.example.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Lob;

public class ProductDto {

	  private Long id;
		
		private String name;
		

		private String description;
		
		private Long price;
		
		
		private byte byteImg[];
		
		private Long categoryId;
		
		private MultipartFile img;
		
		private String categoryName;
		
		
		
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public byte[] getByteImg() {
			return byteImg;
		}

		public void setByteImg(byte[] byteImg) {
			this.byteImg = byteImg;
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public MultipartFile getImg() {
			return img;
		}

		public void setImg(MultipartFile img) {
			this.img = img;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		
		
}
