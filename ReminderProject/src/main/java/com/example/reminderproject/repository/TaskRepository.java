package com.example.reminderproject.repository;

import com.example.reminderproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);
    List<Task> getTasksByProject_id(Long ProjId);
}
