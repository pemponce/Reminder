package com.example.reminderproject.service.impl;

import com.example.reminderproject.exception.UserCannotBeAddedToProjectException;
import com.example.reminderproject.exception.UserNotAllowedToThisProjectException;
import com.example.reminderproject.exception.UserNotAuthorException;
import com.example.reminderproject.exception.UserNotFoundException;
import com.example.reminderproject.model.ProjectRole;
import com.example.reminderproject.model.ProjectUsers;
import com.example.reminderproject.repository.ProjectUsersRepository;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectUsersServiceImpl implements ProjectUsersService {

    private final ProjectUsersRepository projectUsersRepository;
    private final UserService userService;

    @Override
    public void create(ProjectUsers projectUsers) {
        projectUsersRepository.save(projectUsers);
    }

    @Override
    public void addUser(String username, Long crewmateId, Long projectId) {

        var currProjectUsers = projectUsersRepository.getProjectUsersByUserIdAndProjectId(userService.getCurrentUser().getId(), projectId);

        if (currProjectUsers.getUserRole().equals(ProjectRole.AUTHOR)) {

            if (userService.getUserById(crewmateId) != null && projectUsersRepository.getProjectUsersByUserIdAndProjectId(crewmateId, projectId) == null) {
                ProjectUsers newUser = ProjectUsers.builder()
                        .userId(crewmateId)
                        .projectId(projectId)
                        .userRole(ProjectRole.CREWMATE)
                        .build();
                projectUsersRepository.save(newUser);
            } else {
                throw new UserCannotBeAddedToProjectException(username);
            }
        } else {
            throw new UserNotAuthorException(username);
        }
    }

    @Override
    public List<ProjectUsers> getProjUsersByProjId(Long projId) {
        return projectUsersRepository.getProjectUsersByProjectId(projId);
    }

    @Override
    public List<ProjectUsers> getProjUsersByUser(Long userId) {
        return projectUsersRepository.getProjectUsersByUserId(userId);
    }

    @Override
    public ProjectUsers getProjUsersByUserIdAndProjId(Long userId, Long projId) {

        if (projectUsersRepository.getProjectUsersByUserIdAndProjectId(userId, projId) == null) {
            throw new UserNotAllowedToThisProjectException(userService.getUserById(userId).getUsername());
        }
        return projectUsersRepository.getProjectUsersByUserIdAndProjectId(userId, projId);
    }

    @Override
    public void changeProjectUserRole(Long userId, Long projId, ProjectRole projectRole) {
        var currUser = userService.getCurrentUser();
        if (getProjUsersByUserIdAndProjId(currUser.getId(), projId).getUserRole().equals(ProjectRole.AUTHOR)) {
            if (getProjUsersByUserIdAndProjId(userId, projId) != null) {

                var targetToEdit = getProjUsersByUserIdAndProjId(userId, projId);

                targetToEdit.setUserRole(projectRole);
                projectUsersRepository.save(targetToEdit);
            } else {
                throw new UserNotFoundException(userId);
            }
        } else {
            throw new UserNotAuthorException(currUser.getUsername());
        }
    }
}
