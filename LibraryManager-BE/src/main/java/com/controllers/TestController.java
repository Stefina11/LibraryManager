package com.controllers;

import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.services.JwtService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate-token")
    public String generateTestToken() {
    	
    	String uniqueUsername = "testuser_" + System.currentTimeMillis();
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
        );

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        	uniqueUsername, 
            "password",
            authorities
        );
        System.out.println("Generating token for: " + userDetails.getUsername());


        return jwtService.generateToken(userDetails);
    }
        
    
    @GetMapping("/check-roles") //<List<String>> 
    public ResponseEntity<String> checkRoles(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        List<String> roles = jwtService.extractRoles(token);
        System.out.println("Ruoli estratti: " + roles); 
        

        logger.info("Ruoli estratti: " + roles);

        return ResponseEntity.ok(String.join(",", roles));
    }
    


    
}

