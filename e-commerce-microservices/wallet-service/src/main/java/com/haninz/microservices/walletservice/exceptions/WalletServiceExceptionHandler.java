package com.haninz.microservices.walletservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletServiceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<WalletServiceErrorResponse> handleException(UserNotFoundException exc){
		WalletServiceErrorResponse error = new WalletServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<WalletServiceErrorResponse> handleException(WalletNotFoundException exc){
		WalletServiceErrorResponse error = new WalletServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<WalletServiceErrorResponse> handleException(InsufficientWalletBalanceException exc){
		WalletServiceErrorResponse error = new WalletServiceErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	// add exception hanlder to catch any exception
	
	@ExceptionHandler
	public ResponseEntity<WalletServiceErrorResponse> handleException(Exception exc){
		WalletServiceErrorResponse error = new WalletServiceErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
