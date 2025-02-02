package com.example.reminderproject.controller;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.model.User;
import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final FriendsService friendsService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public UserDto currentUserInfo() {
        LOGGER.info(String.format("Пользователь %s -> просматривает свой профиль", userService.getCurrentUser().getUsername()));
        return userMapper.toUserDto(userService.getCurrentUser());
    }

    @GetMapping("/friends")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<UserDto> getUserFriends() {
        LOGGER.info(String.format("Пользователь %s -> просматривает своих друзей", userService.getCurrentUser().getUsername()));
        return friendsService.findFriends();
    }
}
