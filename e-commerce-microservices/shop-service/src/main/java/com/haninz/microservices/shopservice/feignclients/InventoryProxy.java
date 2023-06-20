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


@FeignClient(name="inventory-service")
public interface InventoryProxy {
	
	
	
	
	
	@GetMapping("/product-api/products/{id}")
	public ItemDto getOneProduct(@PathVariable Long id);
	
	@PutMapping("/product-api/products/{productId}/{quantity}")
	public void updateProductQuantity(@PathVariable Long productId, @PathVariable int quantity);



}
