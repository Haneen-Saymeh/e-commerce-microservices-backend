package com.haninz.microservices.walletservice.exceptions;

public class WalletNotFoundException extends RuntimeException {
	
	public WalletNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public WalletNotFoundException(String message) {
		super(message);
		
	}

	public WalletNotFoundException(Throwable cause) {
		super(cause);
		
	}
	

}
