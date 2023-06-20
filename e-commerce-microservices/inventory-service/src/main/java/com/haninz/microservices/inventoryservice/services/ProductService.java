package com.haninz.microservices.inventoryservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.haninz.microservices.inventoryservice.exceptions.ProductNotFoundException;
import com.haninz.microservices.inventoryservice.models.Product;
import com.haninz.microservices.inventoryservice.repositories.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findProducts(){
		return productRepo.findAll();
	}
	
	public Product getProduct(Long id) {
		Optional<Product> product = productRepo.findById(id);
		
		if (product.isEmpty()) {
			 throw new ProductNotFoundException("Product not found with id: " + id);
		}
		
		 return product.get();
	}
	
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
		
	}


}
