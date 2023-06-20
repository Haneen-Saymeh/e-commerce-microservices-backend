package com.haninz.microservices.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.shopservice.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem,Long>{

}
