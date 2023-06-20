package com.haninz.microservices.walletservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haninz.microservices.walletservice.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    List<Role> findAll();
    
    Role findByName(String name);

}
