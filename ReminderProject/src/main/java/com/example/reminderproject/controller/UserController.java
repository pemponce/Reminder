package com.example.reminderproject.controller;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public UserDto currentUserInfo() {
        LOGGER.info(String.format("Пользователь %s -> просматривает свой профиль", userService.getCurrentUser().getUsername()));
        return userMapper.toUserDto(userService.getCurrentUser());
    }
}
