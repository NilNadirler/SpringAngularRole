package com.example.ecommerce.entities;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.enums.OrderStatus;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;


@Entity
@Table(name="orders")
public class Order {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private String orderDescription;
	
	private Date date;

	private Long amount =0L;
	
	private String address;
	
	private String payment;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
	private Long totalAmount =0L;
	
	private Long discount;
	
	private UUID trackingId;
	
	
	public Coupon getCoupon() {
		return coupon;
	}


	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="coupon_id", referencedColumnName="id")
	private Coupon coupon;
	
	@OrderBy("id ASC")
	@OneToMany(fetch= FetchType.LAZY, mappedBy="order")
	private List<CartItems>cartItems;
	
	public OrderDto orderDto(){
		
		OrderDto orderDto = new OrderDto();
		
		orderDto.setId(id);
		orderDto.setAmount(amount);
		orderDto.setAddress(address);
		orderDto.setDate(date);
		orderDto.setOrderStatus(orderStatus);
		orderDto.setTrackingId(trackingId);
		orderDto.setTotalAmount(totalAmount);
		orderDto.setOrderDescription(orderDescription);
		if(coupon!=null) {
			orderDto.setCouponName(coupon.getName());
		}
		
		return orderDto;
		
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	
	
	
	
	
	
}
