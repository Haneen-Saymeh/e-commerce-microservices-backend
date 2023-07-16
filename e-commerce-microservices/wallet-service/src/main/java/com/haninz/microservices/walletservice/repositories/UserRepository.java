package com.haninz.microservices.walletservice.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.walletservice.models.User;
@Repository
public interface UserRepository extends JpaRepository <User, Long>{
	//User findByUsername(String username);
	User findAllById(Long id);
	  Optional<User> findByEmail(String email);
	    Optional<User> findByUsernameOrEmail(String username, String email);
	    User findByUsername(String username);
	    User existsByUsername(String username);
	    Boolean existsByEmail(String email);

}
