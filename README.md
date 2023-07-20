
![ecommerce-microservice-architecture](https://github.com/Haneen-Saymeh/e-commerce-microservices-backend/blob/master/e-commerce-microservices-architecture.JPG)
# E-commerce app using microservices

The project is an e-commerce application that consists of three microservices: wallet, shop, and inventory.


## Tech Stack

* Spring Boot
* Spring Cloud config server
* Spring cloud gateway
* Spring Data JPA
* Eureka Service registry
* Resilience4j circuit breaker
* MySQL
* Maven
* Docker

## Docker hub and images
* https://hub.docker.com/u/haninz
* haninz/cms-naming-server:0.0.1-SNAPSHOT
* haninz/cms-api-gateway:0.0.1-SNAPSHOT
* haninz/cms-spring-cloud-config-server:0.0.1-SNAPSHOT
* haninz/cms-shop-service:0.0.1-SNAPSHOT
* haninz/cms-wallet-service:0.0.1-SNAPSHOT
* haninz/cms-inventory-service:0.0.1-SNAPSHOT



## Features

### Wallet Microservice:

* User registration
* Wallet creation and management
* Deposit and withdrawal of funds
* Transaction history
* RESTful API endpoints

### Shop Microservice:

* Shopping cart management
* Order creation and management
* Payment processing
* RESTful API endpoints

### Inventory Microservice:

* Product creation and management
* Inventory management
* RESTful API endpoints

