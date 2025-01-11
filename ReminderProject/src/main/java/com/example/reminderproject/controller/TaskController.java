package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.UserNotAllowedToThisProjectException;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{projectId}/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final static Logger LOGGER= LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectUsersService projectUsersService;


    @PostMapping("/create")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public void taskCreation(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        var currUser = userService.getCurrentUser();
        var project = projectService.getProjectById(projectId);

        if (project == null) {
            throw new ProjectNotFoundException(projectId);
        }

        if (project.getUserId().equals(currUser.getId())) {
            taskDto.setProject(project);
            taskService.createTask(taskDto);
            LOGGER.info(String.format("%s -> Создал новый таск (%s)", currUser.getUsername(), taskDto.getTitle()));
            LOGGER.info(String.format("content (%s)", taskDto.getContent()));
        } else {
            boolean isMember = projectUsersService.getProjUsersByProjId(project.getId())
                    .stream()
                    .anyMatch(proj -> proj.getUserId().equals(currUser.getId()));

            if (isMember) {
                taskDto.setProject(project);
                taskService.createTask(taskDto);
                LOGGER.info(String.format("%s -> Создал новый таск (%s)", currUser.getUsername(), taskDto.getTitle()));

            } else {
                throw new UserNotAllowedToThisProjectException(currUser.getUsername());
            }
        }
    }

    @PostMapping("/{task_id}/edit")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public void taskEdit(@PathVariable Long task_id, @PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        var currUser = userService.getCurrentUser();
        var project = projectService.getProjectById(projectId);
        var task = taskService.getTaskById(task_id);

        if (project.getUserId().equals(currUser.getId())) {
            taskMapper.apply(task, taskDto);
            taskService.editTask(task);

            LOGGER.info(String.format("%s -> Отредактировал новый таск (%s)", currUser.getUsername(), taskService.getTaskById(task_id).getTitle()));
        }  else {
            throw new UserNotAllowedToThisProjectException(currUser.getUsername());
        }
    }

    @GetMapping("/show")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<TaskDto> projectTaskList(@PathVariable Long projectId) {

        return taskService.getTasksByProjectId(projectId);
    }
}
