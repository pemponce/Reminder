package com.example.reminderproject.service;

import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    void create(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    Optional<User> findUserById(Long id);
    User getUserById(Long id);
    boolean authenticate(String email, String password);
    UserDetailsService userDetails();
}
