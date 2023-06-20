package com.haninz.microservices.walletservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haninz.microservices.walletservice.models.User;
import com.haninz.microservices.walletservice.models.Wallet;
import com.haninz.microservices.walletservice.models.PaymentTransactionHistory;
import com.haninz.microservices.walletservice.services.PaymentTransactionHistoryService;
import com.haninz.microservices.walletservice.services.UserService;
import com.haninz.microservices.walletservice.services.WalletService;

@RestController
@RequestMapping("/wallet-api")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PaymentTransactionHistoryService transactionService;
	
	@GetMapping("/wallets")
	public List<Wallet> getAllWallets(){
		return walletService.findWallets();
	}
	
   
	
	@GetMapping("/wallets/{id}")
	public Wallet getOneWallet(@PathVariable Long id) {
		return walletService.getWallet(id);
		
	}
	
	
	@DeleteMapping("/wallets/{id}")
	public String deleteWallet(@PathVariable Long id) {
		walletService.deleteWallet(id);
		 return "Wallet has been deleted" + id;
		
	}
	
	
	@PutMapping("/wallets")
	public Wallet updateWallet(@RequestBody Wallet wallet){
		 return walletService.saveWallet(wallet);
	}
	
	
	@PostMapping("/wallets/{userId}/deposit")
	public String walletDeposit(@PathVariable Long userId,@RequestBody Double amount){
		 walletService.deposit(userId, amount);
		 return "amount: " +amount+" has been added!";
	}
	
	
	@PostMapping("/wallets/{orderId}/pay")
	public void payUserOrder(@PathVariable Long orderId) {
		
		
		 walletService.payTheOrder(orderId);
		
	}
	
	
	@GetMapping("/wallets/transacftions/{walletId}")
	public List<PaymentTransactionHistory> getWalletTransactions(@PathVariable Long walletId) {
		Wallet wallet = walletService.getWallet(walletId);
		return wallet.getTransactions();
		
	}
	
	
	
	
	
		

}
