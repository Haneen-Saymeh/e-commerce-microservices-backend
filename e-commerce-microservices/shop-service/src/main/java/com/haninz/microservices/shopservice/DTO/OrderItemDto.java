package com.haninz.microservices.shopservice.DTO;

public class OrderItemDto {
	private Long productId;
	private int quantity;
	
	public OrderItemDto() {
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
