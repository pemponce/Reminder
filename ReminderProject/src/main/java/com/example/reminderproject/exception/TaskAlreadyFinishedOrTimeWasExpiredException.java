package com.example.reminderproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TaskAlreadyFinishedOrTimeWasExpiredException extends RuntimeException implements Logg{

    public TaskAlreadyFinishedOrTimeWasExpiredException() {
        super(String.format("Задача уже была выполнена или время задачи истекло"));
        LOGGER().error(String.format("Задача уже была выполнена или время задачи истекло"));

    }
}
