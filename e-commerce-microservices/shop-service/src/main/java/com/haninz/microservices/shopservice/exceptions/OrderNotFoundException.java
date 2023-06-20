package com.haninz.microservices.shopservice.exceptions;

public class OrderNotFoundException extends RuntimeException{
	
	 public OrderNotFoundException(String message) {
	        super(message);
	    }

}
