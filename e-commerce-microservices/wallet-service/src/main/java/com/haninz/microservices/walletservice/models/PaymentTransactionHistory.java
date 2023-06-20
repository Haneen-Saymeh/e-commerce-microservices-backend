package com.haninz.microservices.walletservice.models;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="transactions")
public class PaymentTransactionHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	private Long orderId;
	private Double amount;
	private String transactionType;
	private LocalDateTime transactionDate;
	
	
	@JsonIgnore
	@ManyToOne()
	 @JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	public PaymentTransactionHistory() {
		
	}

	public PaymentTransactionHistory(Long userId, Double amount, String transactionType, Wallet wallet, LocalDateTime transactionDate) {
		this.userId = userId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.wallet = wallet;
		this.transactionDate=transactionDate;
	}

	public PaymentTransactionHistory(Long userId, Long orderId, Double amount, String transactionType, Wallet wallet, LocalDateTime transactionDate) {
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.wallet = wallet;
		this.transactionDate=transactionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
	
	
	
	
	

}
