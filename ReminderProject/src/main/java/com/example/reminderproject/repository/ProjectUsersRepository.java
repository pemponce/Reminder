package com.example.reminderproject.repository;

import com.example.reminderproject.model.ProjectUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUsersRepository extends JpaRepository<ProjectUsers, Long> {

    List<ProjectUsers> getProjectUsersByProjectId(Long projId);
    List<ProjectUsers> getProjectUsersByUserId(Long projId);
    ProjectUsers getProjectUsersByUserIdAndProjectId(Long userId, Long projId);
}
