package com.example.reminderproject.service;

import com.example.reminderproject.model.Project;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findProjectByProjectName(String projectName);

    Project getProjectByProjectName(String projectName);
    Optional<List<Project>> findProjectsByUser_id(Long userId);
    List<Project> getProjectsByUser_id(Long userId);
}
