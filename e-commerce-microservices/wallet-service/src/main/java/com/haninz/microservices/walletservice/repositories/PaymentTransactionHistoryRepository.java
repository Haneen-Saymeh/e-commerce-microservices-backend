package com.haninz.microservices.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.haninz.microservices.walletservice.models.PaymentTransactionHistory;

@Repository
public interface PaymentTransactionHistoryRepository extends JpaRepository <PaymentTransactionHistory,Long>{
	
	PaymentTransactionHistory findByUserId(Long userId);
	
	PaymentTransactionHistory findByOrderId(Long orderId);

}
