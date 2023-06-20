package com.haninz.microservices.walletservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.haninz.microservices.walletservice.dto.PaymentDto;

@FeignClient(name="shop-service")
public interface ShopProxy {
	
	@GetMapping("/order-api/orders/{orderId}")
	public PaymentDto showOrder(@PathVariable Long orderId);
	
	@PutMapping("/order-api/orders/{orderId}/status")
    public void updateOrderStatusToPaid(@PathVariable Long orderId);

}
