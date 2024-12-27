package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNameAlreadyExistException extends RuntimeException implements  Logg {

    public ProjectNameAlreadyExistException(String projName) {
        super(String.format("Проект с именем %s уже существует ", projName));
        LOGGER.error(String.format("Проект с именем %s уже существует ", projName));
    }

}
