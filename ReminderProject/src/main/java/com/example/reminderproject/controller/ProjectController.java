package com.example.reminderproject.controller;

import com.example.reminderproject.dto.ProjectDto;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping("/create")
    public void create(@RequestBody ProjectDto projectDto) {
        projectService.create(projectDto);
    }
}
