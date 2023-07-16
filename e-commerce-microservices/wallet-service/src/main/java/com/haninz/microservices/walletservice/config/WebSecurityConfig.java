//package com.haninz.microservices.walletservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    private UserDetailsService userDetailsService;
//
//    public WebSecurityConfig(UserDetailsService userDetailsService){
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//   
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//                                 AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    	 http.cors().and().csrf().disable()
//                .authorizeHttpRequests((authorize) ->
//                        //authorize.anyRequest().authenticated()
//                        authorize.requestMatchers("/api/**").permitAll()
////                                .requestMatchers("/api/**").permitAll()
////                                .requestMatchers("/wallet-api/**").permitAll()
//                               // .requestMatchers("/wallet-api/**").hasRole("USER")
//                             .anyRequest().authenticated()
//
//                );
//
//        return http.build();
//    }
//}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
	
//	@Autowired
//	private UserDetailsService userDetailsService;

  //@SuppressWarnings("deprecation")
//@Bean
//  public SecurityFilterChain app(HttpSecurity http) throws Exception {
//      http.csrf().disable().cors().and().authorizeRequests().requestMatchers(HttpMethod.OPTIONS,"/").permitAll()
//      .requestMatchers("/register").permitAll()
//      .anyRequest().authenticated();
////      .and()
////      .formLogin()
////      .loginPage("/login")
////		.loginProcessingUrl("/authenticateTheUser")
////		.permitAll();
//      
//      
//      return http.build();
//  }
  
 // @Bean
 // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//      http.csrf().disable()
//          .authorizeRequests()
//              .requestMatchers("/register/**").permitAll()
//              //.antMatchers("/wallet-api/**").hasRole("user")
//              .anyRequest().authenticated()
//          .and()
//          .formLogin()
//              .loginPage("/login")
//              .loginProcessingUrl("/authenticateTheUser")
//              .defaultSuccessUrl("/home")
//              .permitAll()
//          .and()
//          .logout()
//              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//              .permitAll();
//        
//      return http.build();
	  
//
	  //http.authorizeRequests()
	
	      //.requestMatchers("/login", "/register").permitAll()
	 // .requestMatchers("/wallet-api/*").authenticated()
//	      .anyRequest().permitAll()
//	    
//	      .and()
//	      .formLogin()
//	      .loginProcessingUrl("/authenticateTheUser")
//	      .permitAll()
//	     .and()
//	     .csrf().disable();
//	      return http.build();
	     
	      
	      
//	      http.authorizeRequests()
//	      .requestMatchers("/login", "/register").permitAll()
//	      .requestMatchers("/**").authenticated()
//	      .and()
//	      .formLogin()
//	      .loginPage("/login")
//          .loginProcessingUrl("/authenticateTheUser")
//          .defaultSuccessUrl("/home")
//	      .permitAll();
//	      return http.build();
//	      
//	  http.csrf().disable()
//	     .authorizeRequests()
//	      //.requestMatchers("/login").permitAll()
//	      .requestMatchers("/register").permitAll()
//	      .anyRequest().authenticated()
//	      .and()
//	      .formLogin()
//	      .loginPage("/login")
//	      .defaultSuccessUrl("/home")
//	      .permitAll()
//	      .and()
//	      .logout()
//	      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	      .permitAll();
//	      return http.build();
	
 // }

//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication()
//          .withUser("user")
//          .password(passwordEncoder().encode("password"))
//          .roles("USER");
//  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }


//  @Bean
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//      return super.authenticationManagerBean();
//  }


//}
		

		

