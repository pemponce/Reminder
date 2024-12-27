package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final static Logger LOGGER= LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final UserService userService;


    @PostMapping("/create")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public void TaskCreation(@RequestBody TaskDto taskDto) {
        if() // проверка на айди проекта проект дожен быть привязан к айди пользователя
        taskService.createTask(taskDto);
    }
}
