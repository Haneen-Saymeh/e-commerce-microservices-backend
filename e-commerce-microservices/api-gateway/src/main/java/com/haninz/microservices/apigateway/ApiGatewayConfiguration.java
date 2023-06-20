package com.haninz.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the API Gateway.
 * Defines the routes and filters for request handling.
 */
@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/catalog/**")
						.uri("lb://catalog-service"))
				.route(p -> p.path("/user-api/**")
						.uri("lb://wallet-service"))
				.route(p -> p.path("/wallet-api/**")
						.uri("lb://wallet-service"))
				.route(p -> p.path("/product-api/**")
						.uri("lb://inventory-service"))
				.route(p -> p.path("/cart-api/**")
						.uri("lb://shop-service"))
				.route(p -> p.path("/order-api/**")
						.uri("lb://shop-service"))
				
				
				.build();
	}

}
