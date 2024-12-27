package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException implements Logg {

    public ProjectNotFoundException(String projectName) {
        super(String.format("Проект %s -> не найден.", projectName));
        LOGGER.error(String.format("Проект %s -> не найден.", projectName));
    }

    public ProjectNotFoundException(Long id) {
        super(String.format("Пользователь с id %d -> не найден.", id));
        LOGGER.error(String.format("Пользователь с id %d -> не найден.", id));
    }
}
