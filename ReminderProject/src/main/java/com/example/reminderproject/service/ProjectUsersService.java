package com.example.reminderproject.service;

import com.example.reminderproject.model.ProjectUsers;

import java.util.List;

public interface ProjectUsersService {
    List<ProjectUsers> getProjUsersByProjId(Long projId);
}
