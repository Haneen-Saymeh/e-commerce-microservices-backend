package com.haninz.microservices.shopservice.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
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
@Table(name="cart_items")
public class CartItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private Long productId;
	private Integer quantity;
	private Double price;
	private String name;
	
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "carts_items", 
        joinColumns = @JoinColumn(name = "cartItem_id"), 
        inverseJoinColumns = @JoinColumn(name = "cart_id")
 )
    private List<Cart> carts;
	
	public CartItem() {
		carts = new ArrayList<>();
		
	}

	public CartItem(Long productId, Double price, Integer quantity,String name) {
		this.productId=productId;
		this.price=price;
		this.quantity=quantity;
		this.name= name;
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

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
