package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.UserDto;
import com.example.reminderproject.exception.UserAddHimselfException;
import com.example.reminderproject.exception.UserAlreadySendRequestToFriendException;
import com.example.reminderproject.exception.UserHaveNoFriendsException;
import com.example.reminderproject.mapper.UserMapper;
import com.example.reminderproject.model.Friends;
import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.FriendsRepository;
import com.example.reminderproject.service.FriendsService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            System.out.println(friendsRepository.findFriendsByUserId(currUser.getId()));

            List<Friends> friends = friendsRepository.findFriendsByUserId(currUser.getId());
            for (Friends fr : friends) {
                usersDto.add(userMapper.toUserDto(userService.getUserById(fr.getFriendId())));
            }
        } else {
            systemMessage("У вас нет друзей");
        }

        return usersDto;
    }

    @Override
    public List<UserDto> getAllUserRequests(Long currentUserId) {
        List<UserDto> usersDto = new ArrayList<>();

        if (friendsRepository.findFriendsByUserIdAndRequestAcceptedFalse(currentUserId).size() > 0) {
            System.out.println(friendsRepository.findFriendsByUserIdAndRequestAcceptedFalse(currentUserId));
            List<Friends> friendsRequest = friendsRepository.findFriendsByUserIdAndRequestAcceptedFalse(currentUserId);

            for (Friends fr : friendsRequest) {
                usersDto.add(userMapper.toUserDto(userService.getUserById(fr.getFriendId())));
            }
        } else {
            systemMessage("У вас нет заявок в друзья");
        }

        return usersDto;
    }

    @Override
    public List<UserDto> getAllFriendsRequests(Long currentUserId) {
        List<UserDto> usersDto = new ArrayList<>();

        if (friendsRepository.findFriendsByFriendIdAndRequestAcceptedFalse(currentUserId).size() > 0) {
            System.out.println(friendsRepository.findFriendsByFriendIdAndRequestAcceptedFalse(currentUserId));
            List<Friends> friendsRequest = friendsRepository.findFriendsByFriendIdAndRequestAcceptedFalse(currentUserId);

            for (Friends fr : friendsRequest) {
                usersDto.add(userMapper.toUserDto(userService.getUserById(fr.getUserId())));
            }
        } else {
            systemMessage("У вас нет заявок в друзья");
        }

        return usersDto;
    }



    @Override
    public User findFriend(Long friendId) {

        try {
            userService.getUserById(friendId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return userService.getUserById(friendId);
    }

    @Override
    public void addFriend(Long friendId) {

        var currUser = userService.getCurrentUser();
        var friend = userService.getUserById(friendId);

        if (Objects.equals(friendId, currUser.getId())) {
            throw new UserAddHimselfException(currUser.getUsername());
        } else if (friendsRepository.getFriendsByUserIdAndFriendId(currUser.getId(), friendId) != null) {
            throw new UserAlreadySendRequestToFriendException(userService.getUserById(friendId).getUsername());
        }

        Friends friendSave = Friends.builder()
                .userId(currUser.getId())
                .friendId(friend.getId())
                .requestAccepted(false)
                .build();

        friendsRepository.save(friendSave);
    }

    @Override
    public void acceptRequest(Long currUserId, Long friendId, boolean response) {

        var request = friendsRepository.getFriendsByUserIdAndFriendId(currUserId, friendId);

        request.setRequestAccepted(response);
        friendsRepository.save(request);

    }

    @Override
    public String systemMessage(String text) {
        return text;
    }
}
