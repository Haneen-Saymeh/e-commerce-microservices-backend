package com.haninz.microservices.shopservice.exceptions;

public class InsufficientStockException extends RuntimeException {
	
	 public InsufficientStockException(String message) {
	        super(message);
	    }

}
