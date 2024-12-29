package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNotAllowedToThisProjectException extends RuntimeException implements Logg{

    public UserNotAllowedToThisProjectException(String username) {
        super(String.format("Пользователь %s не является членом данного проекта", username));
        LOGGER().error(String.format("Пользователь %s не является членом данного проекта", username));
    }
}
