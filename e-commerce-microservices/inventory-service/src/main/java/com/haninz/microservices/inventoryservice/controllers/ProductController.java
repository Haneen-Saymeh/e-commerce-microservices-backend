package com.haninz.microservices.inventoryservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.haninz.microservices.inventoryservice.models.Product;
import com.haninz.microservices.inventoryservice.services.ProductService;



@RestController
@RequestMapping("/product-api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.findProducts();
	}
	
	
	@GetMapping("/products/{id}")
	public Product getOneProduct(@PathVariable Long id) {
		return productService.getProduct(id);
		
	}
	
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		 return "Product has been deleted" + id;
		
	}
	
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product){
		 return productService.saveProduct(product);
	}
	
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
	    
	    Product currentProduct = productService.getProduct(id);
	    
	    currentProduct.setName(updatedProduct.getName());
	    currentProduct.setDescription(updatedProduct.getDescription());
	    currentProduct.setPrice(updatedProduct.getPrice());
	    currentProduct.setStock(updatedProduct.getStock());
	    return productService.saveProduct(currentProduct);
	}
	
	

	@PutMapping("/products/{productId}")
	public Product increaseProductQuantity(@PathVariable Long productId, @RequestBody int quantity){
		Product product = productService.getProduct(productId);
		int productQuanity = product.getStock();
		product.setStock(productQuanity+quantity);
		 return productService.saveProduct(product);
	}
	
	
//	
	
	@PutMapping("/products/{productId}/{quantity}")
	public void updateProductQuantity(@PathVariable Long productId, @PathVariable int quantity) {
		 Product product = productService.getProduct(productId);
	        int currentStock = product.getStock();
	        int updatedStock = currentStock - quantity;
	        product.setStock(updatedStock);
	        
	        productService.saveProduct(product);
		
	}

}
