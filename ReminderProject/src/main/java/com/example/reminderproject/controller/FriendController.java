package com.example.reminderproject.controller;

import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
