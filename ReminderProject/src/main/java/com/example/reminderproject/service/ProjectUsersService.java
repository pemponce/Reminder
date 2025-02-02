package com.example.reminderproject.service;

import com.example.reminderproject.model.ProjectRole;
import com.example.reminderproject.model.ProjectUsers;

import java.util.List;

public interface ProjectUsersService {
    void create(ProjectUsers projectUsers);
    void addUser(String username, Long crewmateId, Long projectId);
    List<ProjectUsers> getProjUsersByProjId(Long projId);
    ProjectUsers getProjUsersByUserIdAndProjId(Long userId, Long projId);
    void changeProjectUserRole(Long userId, Long projId, ProjectRole projectRole);
}
