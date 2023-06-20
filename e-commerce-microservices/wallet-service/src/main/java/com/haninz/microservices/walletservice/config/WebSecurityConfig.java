//package com.haninz.microservices.walletservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
////Imports omitted for brevity
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//  @SuppressWarnings("deprecation")
////@Bean
////  public SecurityFilterChain app(HttpSecurity http) throws Exception {
////      http.csrf().disable().cors().and().authorizeRequests().requestMatchers(HttpMethod.OPTIONS,"/").permitAll()
////      .requestMatchers("/register").permitAll()
////      .anyRequest().authenticated();
//////      .and()
//////      .formLogin()
//////      .loginPage("/login")
//////		.loginProcessingUrl("/authenticateTheUser")
//////		.permitAll();
////      
////      
////      return http.build();
////  }
//  
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      http.csrf().disable()
//              .authorizeHttpRequests()
//                  .requestMatchers("/register/**").permitAll()
//                  .requestMatchers("/wallet-api/**").hasRole("User")
//                  .anyRequest().authenticated()
//              .and()
//              .formLogin()
//                  .loginPage("/login")
//                  .loginProcessingUrl("/authenticateTheUser")
//                  .defaultSuccessUrl("/wallet-api/wallets")
//                  .permitAll()
//              .and()
//              .logout()
//                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                  .permitAll();
//      
//      return http.build();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }
//
//}
//		
//
//		
//
