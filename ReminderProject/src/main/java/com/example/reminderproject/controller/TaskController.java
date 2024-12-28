package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TaskDto;
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
        if(project.getId().equals(currUser.getId())) {
            taskDto.setProject_id(project.getId());
            taskService.createTask(taskDto);
        } else {
            for (var proj : projectUsersService.getProjUsersByProjId(project.getId())) {
                if(proj.getUserId().equals(currUser.getId()) && proj.getProjectId().equals(project.getId())) {
                    if (proj.getUserId().equals(currUser.getId())) {
                        taskService.createTask(taskDto);
                    }
                    break;
                }
            }

            LOGGER.error(String.format("%s не являетесь членом этого проекта", userService.getCurrentUser().getUsername()));
        }
    }
}
