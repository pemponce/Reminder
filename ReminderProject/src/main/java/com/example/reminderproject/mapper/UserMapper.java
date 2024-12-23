package com.example.reminderproject.mapper;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.model.User;

public interface UserMapper {
    UserDto toUserDto(User user);
}
