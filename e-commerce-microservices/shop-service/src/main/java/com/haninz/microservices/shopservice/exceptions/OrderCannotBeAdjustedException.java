package com.haninz.microservices.shopservice.exceptions;

public class OrderCannotBeAdjustedException extends RuntimeException{
	public OrderCannotBeAdjustedException(String message) {
        super(message);
    }

}
