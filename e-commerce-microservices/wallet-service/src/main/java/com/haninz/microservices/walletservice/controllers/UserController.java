package com.haninz.microservices.walletservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.haninz.microservices.walletservice.dto.UserWalletDto;
import com.haninz.microservices.walletservice.models.User;
import com.haninz.microservices.walletservice.models.Wallet;
import com.haninz.microservices.walletservice.services.UserService;

import jakarta.validation.Valid;
@RequestMapping("/user-api")
@RestController
public class UserController {
	
	@Autowired
    private  UserService userService;
	
//	@Autowired
//    private  PasswordEncoder passwordEncoder;

	
    

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
    	
//    	  User existingUser = userRepository.findByUsername(user.getUsername());
//
//    	    if (existingUser != null) {
//    	        return ResponseEntity.badRequest().body("Username is already taken");
//    	    }

//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

       userService.saveWithUserWallet(user);
       return "user has been added";
    }
    
    
    @GetMapping("/users")
	public ResponseEntity<List<User>> getAllusers(){
		List<User> users = userService.findAllUsers();
		if (users == null) {
			System.out.println("error");
		}
		return ResponseEntity.ok(users);
	}
    
   
    
    @GetMapping("/users/{userId}/wallet")
    public Wallet getUserWallet(@PathVariable("userId") Long userId) {
    	User user = userService.findUser(userId);
    	Wallet userWallet = user.getWallet();
    	
    	return userWallet;	
    }
    
    @PutMapping("/users")
	public User updateUser(@RequestBody User user){
		 return userService.saveUser(user);
	}
    
	
	
	@GetMapping("/users/{id}")
	public User getOneUser(@PathVariable Long id) {
		return userService.findUser(id);}
	
	
	@GetMapping("/users/wallet/{userId}")
	public UserWalletDto getUserWalletDto(@PathVariable Long userId) {
		User user = userService.findUser(userId);
		UserWalletDto userWalletDto = new UserWalletDto();
		userWalletDto.setUserId(user.getId());
		userWalletDto.setWalletBalance(user.getWallet().getBalance());
		return userWalletDto;	
	}
    
	
    
}
