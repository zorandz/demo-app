package com.zoran.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoran.demo.dto.Purchase;
import com.zoran.demo.dto.PurchaseResponse;
import com.zoran.demo.services.CheckoutService;

@RestController
@RequestMapping("/checkout")
//@CrossOrigin("http://localhost:4200")
public class CheckoutController {

	private CheckoutService checkoutService;
	
	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase") 
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
	
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		
		return purchaseResponse;
	}
	
}
