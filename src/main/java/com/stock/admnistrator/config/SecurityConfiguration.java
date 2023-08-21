package com.stock.admnistrator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
//	IN CONSTRUCTION
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
////		 http.csrf((c) -> c
////				.disable()).authorizeHttpRequests((requests) -> requests
////						.requestMatchers("/api/***").permitAll()
////						.anyRequest().authenticated()).sessionManagement((session) -> session
////								.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////				.authenticationProvider(authenticationProvider())
////				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
////				.httpBasic(withDefaults());
//		
//		http
//		.csrf((c) -> c
//		.disable())
//		.authorizeHttpRequests((requests) -> requests
//		.requestMatchers("/api/**")
//		.permitAll()
//		.anyRequest()
//		.authenticated())
//		.sessionManagement((session) -> session
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.authenticationProvider(authenticationProvider())
//		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//		 
//		 return http.build();
//	}
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		AuthenticationProvider authenticationProvider = new AuthenticationProvider();
//		
//		return authenticationProvider;
//	}

}
