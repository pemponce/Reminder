package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email) {
        super(String.format("Аккаунта зарегестрированного на адрес %s, не существует", email));
    }
}
