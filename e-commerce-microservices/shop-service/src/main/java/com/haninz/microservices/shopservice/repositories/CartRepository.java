package com.haninz.microservices.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.shopservice.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

	Cart getByUserId(Long userId);
	Cart findByUserId(Long userId);

}
