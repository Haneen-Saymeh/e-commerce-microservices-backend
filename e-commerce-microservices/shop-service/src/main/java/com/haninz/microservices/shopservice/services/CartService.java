package com.haninz.microservices.shopservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.haninz.microservices.shopservice.feignclients.InventoryProxy;
import com.haninz.microservices.shopservice.feignclients.WalletProxy;
import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.exceptions.CartNotFoundException;
import com.haninz.microservices.shopservice.exceptions.CartItemNotFoundException;
import com.haninz.microservices.shopservice.exceptions.InsufficientStockException;
import com.haninz.microservices.shopservice.repositories.CartItemRepository;
import com.haninz.microservices.shopservice.repositories.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private InventoryProxy inventoryProxy;
	
	@Autowired
	private WalletProxy walletProxy;
	
	
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private CartItemService cartItemService;
	
	
	
	public Cart createCart(Long userId) throws Exception {
		Long id = walletProxy.getUserWalletDto(userId).getUserId();
		
		 if (cartRepo.findByUserId(id) !=null) {
		        throw new Exception("The user already has a shopping cart");
		    }
		Cart newCart= new Cart();
		newCart.setUserId(id);
		newCart.setName("Customer Cart");
		return cartRepo.save(newCart);
		
	}
	
	
	public List<CartItem> addItemToCart(Long cartId,Long productId,Integer quantity) throws Exception {
		Optional<Cart> cart = cartRepo.findById(cartId);
		Cart theCart = cart.get();
		Double price = inventoryProxy.getOneProduct(productId).getPrice();
		Integer productStock= inventoryProxy.getOneProduct(productId).getStock();
		String productName= inventoryProxy.getOneProduct(productId).getName();
		if (quantity > productStock) {
			throw new InsufficientStockException("Not enough product quanity");
		}
		CartItem cartItem = new CartItem(productId,price,quantity,productName);
		cartItemRepo.save(cartItem);
		List<CartItem> cartItems = theCart.getCartItems();
		cartItems.add(cartItem);
		theCart.setCartItems(cartItems);
		cartRepo.save(theCart);
		return theCart.getCartItems();
	}
	
	
	public void saveCart(Cart cart) {
		 cartRepo.save(cart);
		
		
	}



	public Cart getCartByUserId(Long userId) {
		Cart cart = cartRepo.getByUserId(userId);
		return cart;
	}

	
	
	public Cart getCart(Long id) {
		Optional<Cart> cart=cartRepo.findById(id);
		if (cart.isEmpty()) {
			throw new CartNotFoundException("Cart id not found "+ id);
		}
		return cart.get();
	}
	
	
	public void deleteCart(Long id) {
		cartRepo.deleteById(id);
	}
	
	
	public List<CartItem> removeItemFromCart(Long cartId, Long cartItemId) {
		Cart cart= getCart(cartId);
		CartItem cartItem = cartItemService.getCartItem(cartItemId);
		List<CartItem> cartItems = cart.getCartItems();
		cartItems.remove(cartItem);
		cart.setCartItems(cartItems);
		cartRepo.save(cart);
		return cart.getCartItems();
		
	}


	public List<CartItem> updateCartItemQuantity(Long cartId, Long itemId, Integer quantity) {
		 Cart cart = getCart(cartId);
	        List<CartItem> cartItems = cart.getCartItems();
	        CartItem cartItemToUpdate = null;

	        for (CartItem item : cartItems) {
	            if (item.getId().equals(itemId)) {
	                cartItemToUpdate = item;
	                break;
	            }
	            //throw new CartItemNotFoundException("Cart item not found with id: " + itemId);
	        }
	        
	        Integer productStock= inventoryProxy.getOneProduct(cartItemToUpdate.getProductId()).getStock();
			
			if (quantity > productStock) {
				throw new InsufficientStockException("Not enough product quanity");
			}

	        cartItemToUpdate.setQuantity(quantity);
	        cartRepo.save(cart);

	        return cart.getCartItems();
	    }
	
	     
	public void emptyItemsFromCart(Long cartId) {
		Optional<Cart> cart = cartRepo.findById(cartId);
        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found with id: " + cartId);
        }
        Cart theCart = cart.get();
        List<CartItem> itemsInCart=theCart.getCartItems();
        for(CartItem item:itemsInCart ) {
        	cartItemRepo.delete(item);
        }
      
		
		
	}
	
	
	

}
