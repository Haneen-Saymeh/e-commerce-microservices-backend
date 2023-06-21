package com.haninz.microservices.walletservice.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.walletservice.dto.PaymentDto;
import com.haninz.microservices.walletservice.exceptions.InsufficientWalletBalanceException;
import com.haninz.microservices.walletservice.exceptions.WalletNotFoundException;
import com.haninz.microservices.walletservice.feignclients.ShopProxy;
import com.haninz.microservices.walletservice.models.PaymentTransactionHistory;
import com.haninz.microservices.walletservice.models.User;
import com.haninz.microservices.walletservice.models.Wallet;
import com.haninz.microservices.walletservice.repositories.PaymentTransactionHistoryRepository;
import com.haninz.microservices.walletservice.repositories.UserRepository;
import com.haninz.microservices.walletservice.repositories.WalletRepository;
@Service
public class WalletService {
	@Autowired
	private WalletRepository walletRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShopProxy shopProxy;
	
	@Autowired
	private PaymentTransactionHistoryRepository transactionRepo;
	
	
	public List<Wallet> findWallets(){
		return walletRepo.findAll();
	}
	
	
	public Wallet getWallet(Long id) {
		Optional<Wallet> wallet = walletRepo.findById(id);
		if (wallet.isEmpty()) {
			throw new WalletNotFoundException("Wallet id not found: "+id);
		}
		
		return wallet.get();
	}
	
	
	
	public void deleteWallet(Long id) {
		walletRepo.deleteById(id);
	}
	
	public Wallet saveWallet(Wallet wallet) {
		return walletRepo.save(wallet);
		
	}
	
	
	 public Wallet deposit(Long userId, Double amount) {
		 Optional<User> user=userRepo.findById(userId);
		 User theUser = user.get();
		 Wallet userWallet = theUser.getWallet();
		 userWallet.setBalance(userWallet.getBalance()+amount);
		 PaymentTransactionHistory transactionHistory= new PaymentTransactionHistory();
		 transactionHistory.setUserId(userId);
		 transactionHistory.setWallet(userWallet);
		 transactionHistory.setAmount(amount);
		 transactionHistory.setTransactionType("Deposite");
		 transactionHistory.setTransactionDate(LocalDateTime.now());
		 transactionRepo.save(transactionHistory);
		 return walletRepo.save(userWallet);
	        
	    }

	    
	    public void payTheOrder(Long orderId) {
	    	Double orderTotal = shopProxy.showOrder(orderId).getTotal();
	    	Long userId=shopProxy.showOrder(orderId).getUserId();
	    	Optional<User> user = userRepo.findById(userId);
	    	Wallet wallet= user.get().getWallet();
	    	Double walletBalance = wallet.getBalance();
	    	 
	    		walletBalance = walletBalance - orderTotal;
	    		wallet.setBalance(walletBalance);
	    		walletRepo.save(wallet);
	    		PaymentTransactionHistory orderTransaction = new PaymentTransactionHistory();
	    		orderTransaction.setAmount(orderTotal);
	    		orderTransaction.setOrderId(orderId);
	    		orderTransaction.setTransactionDate(LocalDateTime.now());
	    		orderTransaction.setTransactionType("Order Payment");
	    		orderTransaction.setWallet(wallet);
	    		orderTransaction.setUserId(userId);
	    		transactionRepo.save(orderTransaction);
	    		shopProxy.updateOrderStatusToPaid(orderId);
	    		
	    	
	    		
	    	
	 
	    }

}
