package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@CrossOrigin
public class TagController {

    private final TagService tagService;
    private final ProjectService projectService;

    @GetMapping("/{projectId}/project_tags")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<TagDto> getProjectTags(@PathVariable Long projectId) {

        return tagService.getAllAllowedProjectTags(projectService.getProjectById(projectId));
    }

    @GetMapping("/{projectId}/project_tags_id")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<Long> getProjectTagsId(@PathVariable Long projectId) {

        return tagService.getAllAllowedProjectTagsId(projectService.getProjectById(projectId));
    }
}
