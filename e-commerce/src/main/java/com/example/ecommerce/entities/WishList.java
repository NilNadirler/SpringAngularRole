package com.example.ecommerce.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.ecommerce.dto.WishListDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class WishList {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch= FetchType.LAZY, optional =false)
	@JoinColumn(name="product_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	
	
	
	public WishListDto getDto() {
		WishListDto dto = new WishListDto();
		dto.setId(id);
		dto.setPrice(product.getPrice());
		dto.setProductDescription(product.getDescription());
		dto.setProductId(product.getId());
		dto.setProductName(product.getName());
		dto.setReturnedImg(product.getImg());
		dto.setUserId(user.getId());

         return dto;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
