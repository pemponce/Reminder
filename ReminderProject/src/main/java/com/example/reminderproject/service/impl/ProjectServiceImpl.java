package com.example.reminderproject.service.impl;

import com.example.reminderproject.exception.ProjectNameAlreadyExistException;
import com.example.reminderproject.exception.ProjectNotFoundException;
import com.example.reminderproject.exception.UserZeroProjectsException;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.repository.ProjectRepository;
import com.example.reminderproject.service.ProjectService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    private Project create(Project project) {
        if (findProjectByProjectName(project.getProjectName()).isPresent()) {
            throw new ProjectNameAlreadyExistException(project.getProjectName());
        }

        return projectRepository.save(project);
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

        if(userService.findUserById(userId).isEmpty()) {
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
