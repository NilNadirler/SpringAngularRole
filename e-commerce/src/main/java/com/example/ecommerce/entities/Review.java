package com.example.ecommerce.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.ecommerce.dto.ReviewDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private Long rating;
	
	private byte[] img;
	

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	
	
	@ManyToOne(fetch= FetchType.LAZY, optional =false)
	@JoinColumn(name="product_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
	
	
	public ReviewDto getDto(){
		ReviewDto reviewDto = new ReviewDto();
		
		reviewDto.setId(id);
		reviewDto.setRating(rating);
		reviewDto.setDescription(description);
		reviewDto.setReturnImg(img);
		reviewDto.setProductId(product.getId());
		reviewDto.setUserId(user.getId());
		reviewDto.setUsername(user.getName());
		
		return reviewDto;
		
		
		
	}


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


	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}

