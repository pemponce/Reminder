package com.example.reminderproject.mapper.impl;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toUserDto(User user) {
        return user != null ? new UserDto(user.getUsername(), user.getEmail()) : null;
    }
}
