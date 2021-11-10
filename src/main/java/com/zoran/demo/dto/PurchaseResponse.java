package com.zoran.demo.dto;


public class PurchaseResponse {

	//sending this as json
	
	private String orderTrackingNumber;
	
	
	public PurchaseResponse(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
	

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
	
	
	
}
