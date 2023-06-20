package com.haninz.microservices.walletservice.dto;



public class PaymentDto {
	
	private Long orderId;
	
    private double total;
	
	private Long userId;
	
	
	public PaymentDto() {
		
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
	
	

}

