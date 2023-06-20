package com.haninz.microservices.shopservice.DTO;

public class UserWalletDto {
	
	private Long userId;
	private Double walletBalance;
	
	
	public UserWalletDto() {
		
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Double getWalletBalance() {
		return walletBalance;
	}


	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	

}
