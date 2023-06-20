package com.haninz.microservices.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	Order findByUserId(Long userId);

}
