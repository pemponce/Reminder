package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class UserHaveNoFriendsException extends RuntimeException implements Logg{

    public UserHaveNoFriendsException(String username) {
        super(String.format("У пользователя %s, нет друзей XD", username));
        LOGGER().error(String.format("У пользователя %s, нет друзей XD", username));
    }
}
