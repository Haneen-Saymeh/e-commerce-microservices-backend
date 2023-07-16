//package com.haninz.microservices.walletservice.services;
//
//import org.springframework.stereotype.Service;
//
//import com.haninz.microservices.walletservice.models.Role;
//import com.haninz.microservices.walletservice.models.User;
//import com.haninz.microservices.walletservice.repositories.UserRepository;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//          User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
//                 .orElseThrow(() ->
//                         new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
//
//        Set<GrantedAuthority> authorities = user
//                .getRoles()
//                .stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }
//}
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	
//	 @Autowired
//	 private UserRepository userRepo;
//
//	  @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        User user = userRepo.findByUsername(username);
//	        if (user == null) {
//	            throw new UsernameNotFoundException("User not found");
//	        }
//	        return new org.springframework.security.core.userdetails.User(
//	            user.getUsername(),
//	            user.getPassword(),
//	            getAuthorities(user)
//	        );
//	    }
//	    
//	    // 2
//	    private List<GrantedAuthority> getAuthorities(User user){
//	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	        for(Role role : user.getRoles()) {
//	            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//	            authorities.add(grantedAuthority);
//	        }
//	        return authorities;
//	    }
//	
//
//}
