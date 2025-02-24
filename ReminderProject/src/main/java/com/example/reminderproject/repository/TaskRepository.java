package com.example.reminderproject.repository;

import com.example.reminderproject.model.Status;
import com.example.reminderproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);
    List<Task> getTasksByProject_id(Long ProjId);
    List<Task> findTasksByStatusAndProjectId(Status status, Long projectId);
    List<Task> findTasksByTitleContainingAndProjectId(String title, Long projectId);
}
