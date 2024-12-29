package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.UserNotAllowedToThisProjectException;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.ProjectUsers;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{projectId}/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final static Logger LOGGER= LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectUsersService projectUsersService;


    @PostMapping("/create")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public void TaskCreation(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        var currUser = userService.getCurrentUser();
        var project = projectService.getProjectById(projectId);

        // Проверка, существует ли проект
        if (project == null) {
            throw new ProjectNotFoundException(projectId);
        }

        if (project.getUserId().equals(currUser.getId())) {
            taskDto.setProject_id(project.getId());
            taskService.createTask(taskDto);
            LOGGER.info(String.format("%s -> Создал новый таск (%s)", currUser.getUsername(), taskDto.getTitle()));
        } else {
            boolean isMember = projectUsersService.getProjUsersByProjId(project.getId())
                    .stream()
                    .anyMatch(proj -> proj.getUserId().equals(currUser.getId()));

            if (isMember) {
                taskDto.setProject_id(project.getId());
                taskService.createTask(taskDto);
                LOGGER.info(String.format("%s -> Создал новый таск (%s)", currUser.getUsername(), taskDto.getTitle()));

            } else {
                throw new UserNotAllowedToThisProjectException(currUser.getUsername());
            }
        }
    }
}
