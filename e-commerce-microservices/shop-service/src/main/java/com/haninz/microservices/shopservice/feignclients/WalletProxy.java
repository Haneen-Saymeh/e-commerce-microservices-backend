package com.haninz.microservices.shopservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.haninz.microservices.shopservice.DTO.UserWalletDto;
import com.haninz.microservices.shopservice.models.ShopUser;





@FeignClient(name="wallet-service")
public interface WalletProxy {
	
	@GetMapping("/user-api/users/wallet/{userId}")
	public UserWalletDto getUserWalletDto(@PathVariable Long userId);
	
	
	@PostMapping("/wallet-api/wallets/{orderId}/pay")
	public void payUserOrder(@PathVariable Long orderId);
	
	

}
