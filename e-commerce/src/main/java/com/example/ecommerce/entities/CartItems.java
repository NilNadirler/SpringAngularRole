package com.example.ecommerce.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.ecommerce.dto.CartItemsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class CartItems {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long price;
	
	private Long quantity;
	
	@ManyToOne(fetch= FetchType.LAZY, optional =false)
	@JoinColumn(name="product_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	public CartItemsDto getCartDto() {
		CartItemsDto cartDto = new CartItemsDto();
		cartDto.setId(id);
		cartDto.setPrice(price);
		cartDto.setProductId(product.getId());
		cartDto.setQuantity(quantity);
		cartDto.setUserId(user.getId());
		cartDto.setProductName(product.getName());
		cartDto.setReturnedImg(product.getImg());
		cartDto.setOrderId(order.getId());
		
		return cartDto;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
	
	
}
