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
	
	public Product updateProudct(Long productId, Product updatedProduct) {
		 Product currentProduct = getProduct(productId);
		    
		    currentProduct.setName(updatedProduct.getName());
		    currentProduct.setDescription(updatedProduct.getDescription());
		    currentProduct.setPrice(updatedProduct.getPrice());
		    currentProduct.setStock(updatedProduct.getStock());
		    productRepo.save(currentProduct);
		    return currentProduct;
	}
	
	public void updateProductStock(Long productId, int quantity) {
		Product product= getProduct(productId);
		int currentStock = product.getStock();
        int updatedStock = currentStock - quantity;
        product.setStock(updatedStock);
        productRepo.save(product);
	}
	
	public Product increaseProductQuantity(Long productId, int quantity) {
		 Product product = getProduct(productId);
		 int productQuanity = product.getStock();
			product.setStock(productQuanity+quantity);
			productRepo.save(product);
			return product;
	}


}
