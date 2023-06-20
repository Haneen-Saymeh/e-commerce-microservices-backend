package com.haninz.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
/**
 * Global filter for logging incoming requests in the API Gateway.
 * This filter intercepts the request and logs the path of the received request using a logger.
 */
@Component
public class LoggingFilter implements GlobalFilter{
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path of the request received -> {}", 
				exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

	

}

