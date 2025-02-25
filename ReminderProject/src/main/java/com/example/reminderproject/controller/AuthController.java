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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        boolean isAuth = userService.authenticate(request.getUsername(), request.getPassword());
        if (isAuth) {
            LOGGER.info(String.format("Вход в аккаунт пользователем -> %s", request.getUsername()));
            return authService.signIn(request);
        } else {
            throw new IncorrectLogInDataException();
        }
    }

    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        if (userService.findUserByEmail(request.getEmail()).isEmpty()) {
            LOGGER.info(String.format("Регистрация пользователя -> %s", request.getUsername()));
            return authService.signUp(request);
        } else {
            throw new UserAlreadyExistException(request.getUsername(), request.getEmail());
        }
    }
}