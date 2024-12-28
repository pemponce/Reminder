package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserZeroProjectsException extends RuntimeException implements Logg {

    public UserZeroProjectsException(String username) {
        super(String.format("Не найдены проекты пользователя %s", username));
        LOGGER().error(String.format("Не найдены проекты пользователя %s", username));
    }
}
