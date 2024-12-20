package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class IncorrectLogInDataException extends RuntimeException{
    public IncorrectLogInDataException() {
        super("Неверные данные для входа в аккаунт, попробуйте другую почту или пароль");
    }
}
