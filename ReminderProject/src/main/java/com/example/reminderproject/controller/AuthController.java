package com.example.reminderproject.controller;

import com.example.reminderproject.dto.SignInRequest;
import com.example.reminderproject.dto.SignUpRequest;
import com.example.reminderproject.dto.jwt.JwtAuthenticationResponse;
import com.example.reminderproject.exception.IncorrectLogInDataException;
import com.example.reminderproject.exception.UserAlreadyExistException;
import com.example.reminderproject.service.AuthService;
import com.example.reminderproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        boolean isAuth = userService.authenticate(request.getEmail(), request.getPassword());
        if (isAuth) {
            return authService.signIn(request);
        } else {
            throw new IncorrectLogInDataException();
        }
    }

    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        if (userService.findUserByEmail(request.getEmail()).isEmpty()) {
            return authService.signUp(request);
        } else {
            throw new UserAlreadyExistException(request.getUsername(), request.getEmail());
        }
    }
}
