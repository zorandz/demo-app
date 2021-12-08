package com.zoran.demo.dto;

import java.util.Date;

public interface Orders {
	
	public Integer getOrderId();
	public Integer getPrice();
	public Date getOrderDate();
	public String getImageURL();
	public Long getProductId();
	public String getProductName();
}
