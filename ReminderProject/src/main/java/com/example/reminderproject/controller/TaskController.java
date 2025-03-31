package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.TaskNotFoundException;
import com.example.reminderproject.exception.UserNotAllowedToThisProjectException;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.model.*;
import com.example.reminderproject.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/{projectId}/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TagService tagService;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectUsersService projectUsersService;

    @PostMapping("/create")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public void taskCreation(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        var project = projectService.getProjectById(projectId);
        var currUser = userService.getCurrentUser();

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
        var projUser = projectUsersService.getProjUsersByUserIdAndProjId(currUser.getId(), projectId);

        if (project.getUserId().equals(currUser.getId()) || (projUser.getUserRole().equals(ProjectRole.AUTHOR) ||
                projUser.getUserRole().equals(ProjectRole.CMD))) {

            taskMapper.apply(task, taskDto);
            taskService.editTask(task);

            LOGGER.info(String.format("%s -> Отредактировал новый таск (%s)", currUser.getUsername(), taskService.getTaskById(task_id).getTitle()));
        } else {
            throw new UserNotAllowedToThisProjectException(currUser.getUsername());
        }
    }

    @PostMapping("{task_id}/statusProcess")
    @Operation(summary = "Доступень только авторизированным пользователям")
    public void taskStatusToInProcess(@PathVariable Long projectId, @PathVariable Long task_id) {
        var project = projectService.getProjectById(projectId);
        var currUser = userService.getCurrentUser();
        var projUser = projectUsersService.getProjUsersByUserIdAndProjId(currUser.getId(), projectId);
        Task task;
        if (project.getUserId().equals(currUser.getId()) || (projUser.getUserRole().equals(ProjectRole.AUTHOR) ||
                projUser.getUserRole().equals(ProjectRole.CMD))) {

            if(taskService.getTaskById(task_id) != null) {
                task = taskService.getTaskById(task_id);
                taskService.switchTaskStatusToInProcess(task);
            } else {
                throw new TaskNotFoundException(task_id);
            }

            LOGGER.info(String.format("%s -> Отредактировал новый таск (%s)", currUser.getUsername(), taskService.getTaskById(task_id).getTitle()));
        } else {
            throw new UserNotAllowedToThisProjectException(currUser.getUsername());
        }
    }

    @PostMapping("{task_id}/statusDone")
    @Operation(summary = "Доступен только авторизированным пользователям")
    public void taskStatusToDone(@PathVariable Long projectId, @PathVariable Long task_id) {
        var project = projectService.getProjectById(projectId);
        var currUser = userService.getCurrentUser();
        var projUser = projectUsersService.getProjUsersByUserIdAndProjId(currUser.getId(), projectId);
        Task task;
        if (project.getUserId().equals(currUser.getId()) || (projUser.getUserRole().equals(ProjectRole.AUTHOR) ||
                projUser.getUserRole().equals(ProjectRole.CMD))) {

            if(taskService.getTaskById(task_id) != null) {
                task = taskService.getTaskById(task_id);
                taskService.switchTaskStatusToDone(task);
            } else {
                throw new TaskNotFoundException(task_id);
            }

            LOGGER.info(String.format("%s -> Отредактировал новый таск (%s)", currUser.getUsername(), taskService.getTaskById(task_id).getTitle()));
        } else {
            throw new UserNotAllowedToThisProjectException(currUser.getUsername());
        }
    }


    @GetMapping("/{task_id}/tags")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<Tag> taskTags(@PathVariable Long task_id, @PathVariable Long projectId) {
        var project = projectService.getProjectById(projectId);
        var task = taskService.getTaskById(task_id);

        if (project == null) {
            throw new ProjectNotFoundException(projectId);
        }

        return tagService.getAllTaskTags(task);
    }


    @GetMapping("/show")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<Task> projectTaskList(@PathVariable Long projectId) {

        return taskService.getTasksByProjectId(projectId);
    }
}
