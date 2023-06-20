package com.haninz.microservices.walletservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.walletservice.models.PaymentTransactionHistory;
import com.haninz.microservices.walletservice.repositories.PaymentTransactionHistoryRepository;

@Service
public class PaymentTransactionHistoryService {
	
	@Autowired
	private PaymentTransactionHistoryRepository transactionRepo;
	
	public PaymentTransactionHistory getOneTransactionbyUserId(Long userId) {
		return transactionRepo.findByUserId(userId);
		
	}
	
	

	public PaymentTransactionHistory getOneTransactionbyOrder(Long orderId) {
		return transactionRepo.findByOrderId(orderId);
		
	}

}
