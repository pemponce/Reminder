package com.example.reminderproject.repository;

import com.example.reminderproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);
}
