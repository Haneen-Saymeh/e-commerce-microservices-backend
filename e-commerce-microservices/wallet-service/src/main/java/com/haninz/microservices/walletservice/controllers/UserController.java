package com.haninz.microservices.walletservice.controllers;

import java.util.Collections;
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
import com.haninz.microservices.walletservice.models.Role;
import com.haninz.microservices.walletservice.models.User;
import com.haninz.microservices.walletservice.models.Wallet;
import com.haninz.microservices.walletservice.repositories.RoleRepository;
import com.haninz.microservices.walletservice.repositories.UserRepository;
import com.haninz.microservices.walletservice.services.UserService;


import jakarta.validation.Valid;


@RequestMapping("/user-api")
@RestController
public class UserController {
	
	@Autowired
    private  UserService userService;
	
//	 @Autowired
//	    private AuthenticationManager authenticationManager;
	
//	@Autowired
//    private  PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private RoleRepository roleRepository;
	 
	 @Autowired
	 private UserRepository userRepository;

	
    
//
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
    	
    	 // User existingUser = userService.findUserByName(user);

    	   

//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

       userService.saveWithUserWallet(user);
       return user;
    }
    
    
//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        //String token = "Bearer " + jwtTokenProvider.generateToken(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//    }
//    
//    
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
//
//        // add check for username exists in a DB
//        if(userRepository.existsByUsername(signUpDto.getUsername())){
//            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        // add check for email exists in DB
//        if(userRepository.existsByEmail(signUpDto.getEmail())){
//            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        // create user object
//        User user = new User();
//        user.setUsername(signUpDto.getUsername());
//        user.setEmail(signUpDto.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//
//        user.setRoles(roleRepository.findByName("ROLE_USER"));
//        
//        
//        Wallet wallet = new Wallet();
//    	wallet.setBalance(0.0);
//    	wallet.setName(user.getUsername()+" name");
//    	user.setWallet(wallet);
//	   // userRepo.save(user);
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//
//    }
//    
    
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
