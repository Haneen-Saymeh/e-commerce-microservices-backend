package com.haninz.microservices.shopservice.DTO;

public class PaymentDto {
	private Long userId;
	private Long orderId;
	private Double orderTotalAmount;
	
	
	public PaymentDto() {
		
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}


	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	

}
