package com.haninz.microservices.shopservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haninz.microservices.shopservice.DTO.ItemDto;
import com.haninz.microservices.shopservice.feignclients.InventoryProxy;
import com.haninz.microservices.shopservice.feignclients.WalletProxy;
import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.CartItem;


import com.haninz.microservices.shopservice.services.CartItemService;
import com.haninz.microservices.shopservice.services.CartService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@RestController
@RequestMapping("/cart-api")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private InventoryProxy inventoryProxy;
	
	@Autowired
	private WalletProxy walletProxy;
	
	
	
	
	@PostMapping("/carts/{userId}")
	public void createCart(@PathVariable Long userId) throws Exception{
		Cart cart=cartService.createCart(userId);
	}
	
	
	@PostMapping("/carts/{id}/empty")
	public String emptyCart( @PathVariable Long id) {
		cartService.emptyItemsFromCart(id);
		 return "cart with id: " + id +" is empty now!";
	}
	
	
	@CircuitBreaker(name = "InventoryCall", fallbackMethod = "InventoryProxyFallback")
	@PostMapping("/carts/{cartId}/add/{productId}")
	public List<CartItem> addToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId, @RequestBody Integer quantity) throws Exception {
	return	cartService.addItemToCart(cartId, productId, quantity);
		
	}
	
	
	public Cart InventoryProxyFallback(Long cartId, Long productId, Integer quantity, Exception ex) {
		 Cart fallbackCart = new Cart();
		    return fallbackCart;
	}
	
	@PostMapping("/carts/{cartId}/remove/{cartItemId}")
	public List<CartItem> removeFromCart(@PathVariable("cartId") Long cartId, @PathVariable("cartItemId") Long cartItemId) {
		 return cartService.removeItemFromCart(cartId, cartItemId);
		
	}
	
	
	@GetMapping("/carts/{cartId}")
	public Cart showMyCart(@PathVariable Long cartId) {
		Cart cart=cartService.getCart(cartId);
		return cart;
	}
	
	
	@GetMapping("/carts/{cartId}/items")
	public List<CartItem> showMyCartItems(@PathVariable Long cartId) {
		Cart cart=cartService.getCart(cartId);
		return cart.getCartItems();
	}
	
	
	@PutMapping("/carts/{cartId}/items/{itemId}")
	public List<CartItem> updateCartItemQuantity(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId, @RequestBody Integer quantity) throws Exception {
	    return cartService.updateCartItemQuantity(cartId, itemId, quantity);
	}

}
