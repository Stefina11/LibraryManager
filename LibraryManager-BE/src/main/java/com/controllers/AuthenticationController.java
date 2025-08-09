package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dtos.LoginResponse;
import com.dtos.LoginUserDto;
import com.dtos.RegisterUserDto;
import com.entities.User;
import com.services.AuthenticationService;
import com.services.JwtService;
import com.services.UserService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
   

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService,UserService userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        System.out.println("Received signup request: " + registerUserDto);

        System.out.println("User registered: " + registeredUser); 
        
    return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint working");
    }



}
