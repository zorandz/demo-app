package com.zoran.demo.services;

import java.util.Set;


import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zoran.demo.repositories.UserRepository;
import com.zoran.demo.dto.Purchase;
import com.zoran.demo.dto.PurchaseResponse;
import com.zoran.demo.entities.Order;
import com.zoran.demo.entities.OrderItem;
import com.zoran.demo.entities.User;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private UserRepository userRepo;
	
	public CheckoutServiceImpl (UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		// populate customer with order
		User user = purchase.getUser();
		
		String theEmail = user.getEmail();
		
		User userFromDB = userRepo.findUserByEmail(theEmail);
		
		if (userFromDB != null) {
			user = userFromDB;
		}
		
		user.add(order);

		userRepo.save(user);
		
		return new PurchaseResponse(orderTrackingNumber);
	}
	
	private String generateOrderTrackingNumber() {
		
		// generate a random UUID number (uuid version - 4)
		// for details see: http://en.wikipedia.org/wiki/universally_unique_identifier
		// java has a built in class UUID.
		
		return UUID.randomUUID().toString();
	}
	
	
	
	

}
