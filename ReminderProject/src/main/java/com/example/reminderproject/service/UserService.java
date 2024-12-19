package com.example.reminderproject.service;

import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User getUserByUsername(String username);
    Optional<User> findUserById(Long id);
    User getUserById(Long id);
    UserDetailsService userDetails();
}
