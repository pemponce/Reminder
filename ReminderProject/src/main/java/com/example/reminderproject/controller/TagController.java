package com.example.reminderproject.controller;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@CrossOrigin
public class TagController {

    private final UserService userService;
    private final TagService tagService;
    private final ProjectService projectService;

    @GetMapping("/{projectId}/project_tags")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public List<TagDto> getProjectTags(@PathVariable Long projectId) {
        userService.getCurrentUser();
        System.out.println("Получение тегов для проекта с ID: " + projectId);

        //логирование для тестов бля
        List<TagDto> tagDtoList = tagService.getAllAllowedProjectTags(projectService.getProjectById(projectId));
        System.out.println("Теги: " + tagDtoList);

        return tagDtoList;
    }

}
