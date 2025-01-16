package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.exception.UserHaveNoFriendsException;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.repository.FriendsRepository;
import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository friendsRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findFriends() {

        var currUser = userService.getCurrentUser();
        List<UserDto> usersDto = new ArrayList<>();

        if (friendsRepository.findFriendsByUserId(currUser.getId()).size() > 0) {

            List<Long> friendsId = friendsRepository.findFriendsByUserId(currUser.getId());
            for (Long id : friendsId) {
                usersDto.add(userMapper.toUserDto(userService.getUserById(id)));
            }
        } else {
            throw new UserHaveNoFriendsException(currUser.getUsername());
        }

        return usersDto;
    }
}
