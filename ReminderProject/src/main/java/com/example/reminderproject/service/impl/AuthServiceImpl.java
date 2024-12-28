package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.SignInRequest;
import com.example.reminderproject.dto.SignUpRequest;
import com.example.reminderproject.dto.jwt.JwtAuthenticationResponse;
import com.example.reminderproject.model.Role;
import com.example.reminderproject.model.User;
import com.example.reminderproject.service.AuthService;
import com.example.reminderproject.service.JwtService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        ));

        UserDetails user = userService.userDetails().loadUserByUsername(signInRequest.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();


        var jwt = jwtService.generateToken(user);
        userService.create(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
