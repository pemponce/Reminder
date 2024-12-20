package com.example.reminderproject.service;

import com.example.reminderproject.dto.SignInRequest;
import com.example.reminderproject.dto.SignUpRequest;
import com.example.reminderproject.dto.jwt.JwtAuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

public interface AuthService {
    JwtAuthenticationResponse signIn(@Valid SignInRequest signInRequest);
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);
}
