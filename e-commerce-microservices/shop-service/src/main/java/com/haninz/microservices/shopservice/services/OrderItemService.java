package com.haninz.microservices.shopservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.models.OrderItem;
import com.haninz.microservices.shopservice.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	
	public void saveOrderItem(OrderItem orderItem) {
		orderItemRepo.save(orderItem);
			
	}
	
	public OrderItem getOrderItem(Long id) {
		Optional<OrderItem> orderItem=orderItemRepo.findById(id);
		return orderItem.get();
	}


}
