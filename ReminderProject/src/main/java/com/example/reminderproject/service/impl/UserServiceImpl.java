package com.example.reminderproject.service.impl;

import com.example.reminderproject.exception.UserNotFoundException;
import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.UserRepository;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User getUserById(Long id) {
        return findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserDetailsService userDetails() {
        return this::getUserByUsername;
    }
}
