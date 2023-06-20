package com.haninz.microservices.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.shopservice.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{

}
