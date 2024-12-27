package com.example.reminderproject.exception;

import com.example.reminderproject.service.UserService;
import com.example.reminderproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserZeroProjectsException extends RuntimeException implements Logg {

    public UserZeroProjectsException(String username) {
        super(String.format("Не найдены проекты пользователя %s", username));
        LOGGER.error(String.format("Не найдены проекты пользователя %s", username));
    }
}
