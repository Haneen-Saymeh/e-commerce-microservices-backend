package com.haninz.microservices.shopservice.exceptions;

public class CartItemNotFoundException extends RuntimeException{
	
	public CartItemNotFoundException(String message) {
        super(message);
    }

}
