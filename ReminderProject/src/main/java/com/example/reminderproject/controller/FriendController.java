package com.example.reminderproject.controller;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.model.User;
import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
@CrossOrigin
public class FriendController {

    private final UserService userService;
    private final FriendsService friendsService;


    @PostMapping("/add/{friendId}")
    public String addFriend(@PathVariable Long friendId) {
        var currUser = userService.getCurrentUser();

        friendsService.addFriend(friendId);
        return String.format("Вы отправили приглашение в друзья пользователю %s", userService.getUserById(friendId).getUsername());
    }

    @GetMapping("/requests")
    public List<UserDto> getAllFriendsRequests() {
        var currUser = userService.getCurrentUser();

        return friendsService.getAllFriendsRequests(currUser.getId());
    }
}
