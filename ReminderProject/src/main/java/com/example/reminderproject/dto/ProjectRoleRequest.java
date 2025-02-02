package com.example.reminderproject.dto;

import com.example.reminderproject.model.ProjectRole;

public class ProjectRoleRequest {
    private String projectRole;

    public ProjectRole getProjectRole() {
        return ProjectRole.valueOf(projectRole);
    }
}