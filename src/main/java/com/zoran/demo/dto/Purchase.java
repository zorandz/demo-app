package com.zoran.demo.dto;

import java.util.Set;


import com.zoran.demo.entities.Address;
import com.zoran.demo.entities.Order;
import com.zoran.demo.entities.OrderItem;
import com.zoran.demo.entities.User;


public class Purchase {
	
	private User user;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
	
	public Purchase(Order order, User user, Address billing,
					Address shipping, Set<OrderItem> orderItems) {
		this.user = user;
		this.billingAddress = billing;
		this.shippingAddress = shipping;
		this.order = order;
		this.orderItems = orderItems;
	}
	
	
	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
}
