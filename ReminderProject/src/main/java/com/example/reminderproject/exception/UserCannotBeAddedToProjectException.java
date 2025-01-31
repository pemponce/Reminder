package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class UserCannotBeAddedToProjectException extends RuntimeException implements Logg {

    public UserCannotBeAddedToProjectException(String username) {
        super(String.format("Пользователь %s не может быть добавлен в проект. %s уже является членом этого проекта, или его не существует", username, username));
        LOGGER().error(String.format("Пользователь %s не может быть добавлен в проект. %s уже является членом этого проекта, или его не существует", username, username));
    }
}
