package com.example.reminderproject.service;

import com.example.reminderproject.model.ProjectUsers;

import java.util.List;

public interface ProjectUsersService {
    void create(ProjectUsers projectUsers);
    void addUser(String username, Long projectId);
    List<ProjectUsers> getProjUsersByProjId(Long projId);
}
