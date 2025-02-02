package com.example.reminderproject.service;

import com.example.reminderproject.dto.ProjectDto;
import com.example.reminderproject.model.Project;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void create(ProjectDto project);
    Optional<Project> findProjectByProjectName(String projectName);

    Project getProjectByProjectName(String projectName);
    Optional<List<Project>> findProjectsByUser_id(Long userId);
    List<Project> getProjectsByUser_id(Long userId);
    Optional<Project> findProjectById(Long id);
    Project getProjectById(Long id);
}
