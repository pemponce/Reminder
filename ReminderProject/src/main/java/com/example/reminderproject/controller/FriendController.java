package com.example.reminderproject.controller;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.model.User;
import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/yours_requests")
    public List<UserDto> getAllUserRequests() {
        var currUser = userService.getCurrentUser();

        return friendsService.getAllUserRequests(currUser.getId());
    }

    @GetMapping("/requests_to_you")
    public List<UserDto> getAllFriendsRequests() {
        var currUser = userService.getCurrentUser();

        return friendsService.getAllFriendsRequests(currUser.getId());
    }

    @PostMapping("/accept/{friendId}")
    public void addRequest(@PathVariable Long friendId, @RequestBody String response) {
        var currUser = userService.getCurrentUser();
        System.out.println(response);

        boolean status = response.contains("0");
        friendsService.acceptRequest(currUser.getId(), friendId, status);
    }
}
