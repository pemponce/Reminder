package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.dto.TextDto;
import com.example.reminderproject.model.Status;
import com.example.reminderproject.model.StatusRequestDto;
import com.example.reminderproject.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor
@CrossOrigin
public class SearchController {

    private final TaskService taskService;

    @GetMapping("/findTaskByStatus")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<TaskDto> searchTaskByStatus(@RequestBody StatusRequestDto status) {
        return taskService.getTasksByStatus(Status.valueOf(status.getStatus()));
    }

    @GetMapping("/findTaskByTitle")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<TaskDto> searchTaskByStatus(@RequestBody TextDto title) {
        return taskService.getTasksByTitle(title.getText());
    }
}
