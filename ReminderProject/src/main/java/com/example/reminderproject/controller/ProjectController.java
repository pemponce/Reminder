package com.example.reminderproject.controller;

import com.example.reminderproject.dto.ProjectDto;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    @PostMapping("/create")
    public void create(@RequestBody ProjectDto projectDto) {
        projectService.create(projectDto);
    }

    @GetMapping("")
    public Optional<List<Project>> show() {

       return projectService.findProjectsByUser_id(userService.getCurrentUser().getId());
    }

    @GetMapping("/getProject/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }
}
