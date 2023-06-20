package com.haninz.microservices.shopservice.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItem {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private Long productId;
		private Integer quantity;
		private Double price;
		
		
		@JsonIgnore
		@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "orders_orderItems", 
	        joinColumns = @JoinColumn(name = "orderItem_id"), 
	        inverseJoinColumns = @JoinColumn(name = "order_id")
	 )
		private List<Order> orders;
		
		public OrderItem() {
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
		
		
	

}
