package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotAuthorException extends RuntimeException implements Logg{

    public UserNotAuthorException(String username) {
        super(String.format("Пользователь %s не может добавить пользователя т.к. не является автором этого проекта", username));
        LOGGER().error(String.format("Пользователь %s не может добавить пользователя т.к. не является автором этого проекта", username));
    }

}
