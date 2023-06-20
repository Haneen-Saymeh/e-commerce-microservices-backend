package com.haninz.microservices.shopservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.repositories.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepo;
	
	public void saveCart(CartItem cartItem) {
		cartItemRepo.save(cartItem);
			
	}
	
	public CartItem getCartItem(Long id) {
		Optional<CartItem> cartItem=cartItemRepo.findById(id);
		return cartItem.get();
	}

}
