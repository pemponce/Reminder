package com.example.reminderproject.controller;

import com.example.reminderproject.config.JwtConfig;
import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public UserDto currentUserInfo() {
        return userMapper.toUserDto(userService.getCurrentUser());
    }
}
