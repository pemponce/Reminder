package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.ProjectDto;
import com.example.reminderproject.exception.ProjectNameAlreadyExistException;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.UserZeroProjectsException;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.ProjectUsers;
import com.example.reminderproject.repository.ProjectRepository;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final ProjectUsersService projectUsersService;
    private final UserService userService;

    public void create(ProjectDto projectDto) {
        if (findProjectByProjectName(projectDto.getProjectName()).isPresent()) {
            throw new ProjectNameAlreadyExistException(projectDto.getProjectName());
        }

        Project project = Project.builder()
                .projectName(projectDto.getProjectName())
                .userId(projectDto.getUserId())
                .build();

        projectRepository.save(project);

        ProjectUsers projectUsers = ProjectUsers.builder()
                .userId(projectDto.getUserId())
                .projectId(project.getId())
                .build();

        projectUsersService.create(projectUsers);

        LOGGER.info(String.format("Создан новый проект -> %s : пользователем -> %s ",
                project.getProjectName(),
                userService.getUserById(project.getUserId()).getUsername()));
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
