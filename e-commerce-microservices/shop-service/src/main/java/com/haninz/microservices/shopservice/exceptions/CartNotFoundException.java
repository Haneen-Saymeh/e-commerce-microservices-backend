package com.haninz.microservices.shopservice.exceptions;

public class CartNotFoundException extends RuntimeException {
	
	 public CartNotFoundException(String message) {
	        super(message);
	    }

}
