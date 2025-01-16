package com.example.reminderproject.service;

import com.example.reminderproject.dto.UserDto;

import java.util.List;

public interface FriendsService {

    List<UserDto> findFriends();

}
