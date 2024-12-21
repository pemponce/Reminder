package com.example.reminderproject.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException implements Logg{

    public UserAlreadyExistException(String username, String email) {
        super(String.format("Пользователь с именем %s или почтой %s уже существует", username, email));
        LOGGER.error(String.format("Пользователь с именем %s или почтой %s уже существует", username, email));

    }
}
