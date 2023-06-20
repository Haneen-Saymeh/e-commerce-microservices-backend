package com.haninz.microservices.walletservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.walletservice.dto.UserWalletDto;
import com.haninz.microservices.walletservice.exceptions.UserNotFoundException;
import com.haninz.microservices.walletservice.models.Role;
import com.haninz.microservices.walletservice.models.User;
import com.haninz.microservices.walletservice.models.Wallet;
import com.haninz.microservices.walletservice.repositories.RoleRepository;
import com.haninz.microservices.walletservice.repositories.UserRepository;
import com.haninz.microservices.walletservice.repositories.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WalletRepository walletRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public User saveWithUserWallet(User user) {
		Wallet wallet = new Wallet();
    	wallet.setBalance(0.0);
    	wallet.setName(user.getUsername()+" name");
    	user.setWallet(wallet);
	    userRepo.save(user);
		return  user;
		
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	
	
	public User findUser(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User id not found: "+id);
		}
		return user.get();
	}
	
	
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}

}
