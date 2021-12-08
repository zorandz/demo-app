package com.zoran.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	
	@Column(name="order_tracking_number")
	private String orderTrackingNumber;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_created")
	@CreationTimestamp  //special anotation from Hybernate
	private Date dateCreated;
	
	@Column(name="last_updated")
	@UpdateTimestamp //another special from hybernate
	private Date lastUpdated;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
	private Address shippingAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;
	
	public Order() {}
	
	
	public Order(Long id, String orderTrackingNumber, int totalQuantity,
				BigDecimal totalPrice, String status, Date dateCreated,
				Date lastUpdated, List<OrderItem> orderItems, User user,
				Address shipping, Address billing) {
		this.id = id;
		this.orderTrackingNumber = orderTrackingNumber;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.orderItems = orderItems;
		this.user = user;
		this.shippingAddress = shipping;
		this.billingAddress = billing;
	}
	
	
	public void add(OrderItem item) {
		
		if (item != null) {
			if (orderItems == null) {
				orderItems = new ArrayList<>();
			}
			
			orderItems.add(item);
			item.setOrder(this);
		}
	}
	
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@JsonBackReference
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





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	
}
