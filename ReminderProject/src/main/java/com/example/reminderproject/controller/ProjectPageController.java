package com.example.reminderproject.controller;

import com.example.reminderproject.dto.ProjectRoleRequest;
import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.model.ProjectRole;
import com.example.reminderproject.model.Task;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project/{projectId}")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectPageController {

    private final TaskService taskService;
    private final ProjectUsersService projectUsersService;
    private final UserService userService;

    @GetMapping
    public List<Task> showPage(@PathVariable Long projectId) {

        return taskService.getTasksByProjectId(projectId);
    }

    @PostMapping("/add_user/{userId}")
    public void addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {

        projectUsersService.addUser(userService.getUserById(userId).getUsername(), userId, projectId);
    }

    @PostMapping("/edit_user_role/{userId}")
    public void editUserRole(@PathVariable Long userId,@PathVariable Long projectId, @RequestBody ProjectRoleRequest projectRole) {

        projectUsersService.changeProjectUserRole(userId, projectId, projectRole.getProjectRole());
    }
}
