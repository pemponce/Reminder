package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException implements Logg{

    public TaskNotFoundException(Long taskId) {
        super(String.format("Задача с id %d не найдена", taskId));
        LOGGER().error(String.format("Задача с id %d не найдена", taskId));
    }
}
