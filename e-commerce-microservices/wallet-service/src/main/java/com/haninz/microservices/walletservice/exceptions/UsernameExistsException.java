package com.haninz.microservices.walletservice.exceptions;

public class UsernameExistsException extends RuntimeException{
	
	public UsernameExistsException(String message) {
		super(message);
		
	}

}
