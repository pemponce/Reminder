package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadySendRequestToFriendException extends RuntimeException implements Logg {

    public UserAlreadySendRequestToFriendException(String username) {
        super(String.format("Ошибка! Вы уже отправили запрос пользователю - %s", username));
        LOGGER().error(String.format("Ошибка! Вы уже отправили запрос пользователю - %s", username));
    }
}
