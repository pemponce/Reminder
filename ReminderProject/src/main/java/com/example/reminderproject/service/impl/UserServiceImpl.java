package com.example.reminderproject.service.impl;

import com.example.reminderproject.exception.EmailNotFoundException;
import com.example.reminderproject.exception.UserAlreadyExistException;
import com.example.reminderproject.exception.UserNotFoundException;
import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.UserRepository;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException(user.getUsername());
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
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
    public boolean authenticate(String email, String password) {
        return getUserByEmail(email) != null && passwordEncoder.matches(password, getUserByEmail(email).getPassword());
    }

    @Override
    public UserDetailsService userDetails() {
        return this::getUserByUsername;
    }
}
