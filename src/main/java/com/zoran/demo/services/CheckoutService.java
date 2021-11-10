package com.zoran.demo.services;

import com.zoran.demo.dto.Purchase;
import com.zoran.demo.dto.PurchaseResponse;


public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
}
