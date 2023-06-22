package com.haninz.microservices.shopservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.haninz.microservices.shopservice.DTO.ItemDto;
import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.models.Order;
import com.haninz.microservices.shopservice.models.OrderItem;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name="inventory-service")
public interface InventoryProxy {
	
	
	
	
	
	@GetMapping("/product-api/products/{id}")
	 @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackGetOneProduct")
	public ItemDto getOneProduct(@PathVariable Long id);
	
	@PutMapping("/product-api/products/{productId}/{quantity}")
	 @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackUpdateProductQuantity")
	public void updateProductQuantity(@PathVariable Long productId, @PathVariable int quantity);
	
	
	 default ItemDto fallbackGetOneProduct(Long id, Throwable throwable) {
	      
	        return new ItemDto();
	    }

	    default void fallbackUpdateProductQuantity(Long productId, int quantity, Throwable throwable) {
	    	  System.out.println("Failed to update product quantity: " + throwable.getMessage());
	    }

	
	








}
