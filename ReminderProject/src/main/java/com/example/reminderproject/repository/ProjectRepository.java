package com.example.reminderproject.repository;

import com.example.reminderproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findProjectByProjectName(String projectName);
    Optional<List<Project>> findProjectsByUserId(Long userId);
    Optional<Project> findProjectById(Long id);

}
