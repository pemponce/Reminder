package com.example.reminderproject.service;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.model.User;

import java.util.List;

public interface FriendsService {
    List<UserDto> findFriends();
    User findFriend(Long friendId);
    void addFriend(Long friendId);
    void acceptRequest(Long currUserId, Long friendId, boolean response);

}
