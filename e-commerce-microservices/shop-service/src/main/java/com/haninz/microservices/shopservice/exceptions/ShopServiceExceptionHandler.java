package com.haninz.microservices.shopservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopServiceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(CartItemNotFoundException exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(CartNotFoundException exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(OrderNotFoundException exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(InsufficientStockException exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(Exception exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ShopServiceErrorResponse> handleException(OrderCannotBeAdjustedException exc){
		ShopServiceErrorResponse error = new ShopServiceErrorResponse(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
