package com.haninz.microservice.catalogservice.models;
/**
 * Creating class model for Item with fields, constructors and getters and setters.

 */
public class Item {
	
	private String name;
	private String category;
	private String description;
	private int quantity;
	private double price;
	private String environment;
	
	
	public Item() {
		
	}


	public Item(String name, String category, String description, int quantity, double price) {
		
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	

}
