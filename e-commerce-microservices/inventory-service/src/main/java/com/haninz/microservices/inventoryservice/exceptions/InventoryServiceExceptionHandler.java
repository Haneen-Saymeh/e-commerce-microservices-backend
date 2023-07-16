package com.haninz.microservices.inventoryservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InventoryServiceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<InventoryServiceErrorResponse> handleException(ProductNotFoundException exc){
		InventoryServiceErrorResponse error = new InventoryServiceErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

}
