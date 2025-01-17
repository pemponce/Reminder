package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAddHimselfException extends RuntimeException implements Logg {

    public UserAddHimselfException(String username) {
        super(String.format("Вы не можете доавить самого себя в друзья - %s", username));
        LOGGER().error(String.format("Вы не можете доавить самого себя в друзья - %s", username));
    }
}
