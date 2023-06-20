package com.haninz.microservices.inventoryservice.exceptions;

public class ProductNotFoundException  extends RuntimeException{
	
	public ProductNotFoundException(String message) {
        super(message);
    }

}
