package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.ProjectDto;
import com.example.reminderproject.exception.ProjectNameAlreadyExistException;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.UserZeroProjectsException;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.ProjectUsers;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.repository.ProjectRepository;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final TagService tagService;
    private final UserService userService;

    public void create(ProjectDto projectDto) {
        // Проверка, существует ли проект с таким именем
        if (findProjectByProjectName(projectDto.getProjectName()).isPresent()) {
            throw new ProjectNameAlreadyExistException(projectDto.getProjectName());
        }

        Project project = Project.builder()
                .projectName(projectDto.getProjectName())
                .userId(userService.getCurrentUser().getId())
                .build();

        project = projectRepository.save(project);

        if (projectDto.getTags() != null && !projectDto.getTags().isEmpty()) {
            Project finalProject = project;
            List<Tag> tags = projectDto.getTags().stream()
                    .map(tagDto -> Tag.builder()
                            .tagName(tagDto.getTagName())
                            .color(tagDto.getColor())
                            .project(finalProject)
                            .build())
                    .collect(Collectors.toList());

            tagService.saveAll(tags);
        }

        LOGGER.info("Создан новый проект: {}", project.getProjectName());
    }


    @Override
    public Optional<Project> findProjectByProjectName(String projectName) {
        return projectRepository.findProjectByProjectName(projectName);
    }

    @Override
    public Project getProjectByProjectName(String projectName) {
        return findProjectByProjectName(projectName).orElseThrow(() -> new ProjectNotFoundException(projectName));
    }

    @Override
    public Optional<List<Project>> findProjectsByUser_id(Long userId) {
        return projectRepository.findProjectsByUserId(userId);
    }

    @Override
    public List<Project> getProjectsByUser_id(Long userId) {

        if (userService.findUserById(userId).isEmpty()) {
            throw new ProjectNotFoundException(userId);
        }

        return findProjectsByUser_id(userId)
                .orElseThrow(() -> new UserZeroProjectsException(userService.getUserById(userId).getUsername()));
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        return findProjectById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
