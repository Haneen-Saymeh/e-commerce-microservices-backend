package com.haninz.microservices.walletservice.exceptions;

public class InsufficientWalletBalanceException extends RuntimeException {
	
	 public InsufficientWalletBalanceException(String message) {
	        super(message);
	    }

}
