package com.example.ecommerce.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.ecommerce.entities.CartItems;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class OrderDto {

	
	private Long id;
	
	
	private String orderDescription;
	
	private Date date;
	
	private Long amount;
	
	private String address;
	
	private String payment;

	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	
	private UUID trackingId;
	
	private String userName;
	
	private String couponName;
	
	private List<CartItemsDto>cartItems =new ArrayList<CartItemsDto>();
	
	
	public String getCouponName() {
		return couponName;
	}
	
	


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrderDescription() {
		return orderDescription;
	}


	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Long getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Long getDiscount() {
		return discount;
	}


	public void setDiscount(Long discount) {
		this.discount = discount;
	}


	public UUID getTrackingId() {
		return trackingId;
	}


	public void setTrackingId(UUID trackingId) {
		this.trackingId = trackingId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<CartItemsDto> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItemsDto> cartItems) {
		this.cartItems = cartItems;
	}



	
}
