package com.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entities.User;
import com.repositories.UserRepository;


@Service
public class UserService {
	
	private final UserRepository userRepository;
	
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + userId));
    }
	
	
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
