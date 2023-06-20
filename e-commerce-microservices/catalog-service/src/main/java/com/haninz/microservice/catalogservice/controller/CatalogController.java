package com.haninz.microservice.catalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haninz.microservice.catalogservice.configuration.Configuration;
import com.haninz.microservice.catalogservice.models.Item;
/**
 * Controller class for handling catalog-related requests.
 * Provides endpoints to retrieve catalog items and associated information.
 */
@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private Configuration configuration;
	@Autowired
	private Environment environment;
	
	 /**
     * Retrieves an item from the catalog.
     * Uses the Configuration bean to populate the item's details from the remote git repo.
     * Additionally, adds the current server port to the item's environment field.
     */
	
	@GetMapping("/item")
	public Item getItem() {
		Item item =  new Item(configuration.getName(),configuration.getCategory(),configuration.getDescription(),configuration.getQuantity(),configuration.getPrice());
		String port = environment.getProperty("local.server.port");
		item.setEnvironment(port);
		return item;
	}

}
