package com.example.reminderproject.repository;

import com.example.reminderproject.model.ProjectUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUsersRepository extends JpaRepository<ProjectUsers, Long> {

    List<ProjectUsers> getProjectUsersByProjectId(Long projId);
}
