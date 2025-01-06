package com.example.reminderproject.service;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.model.Status;
import com.example.reminderproject.model.Task;

import java.util.List;

public interface TaskService {
    void createTask(TaskDto task);
    Task getTaskById(Long id);
    Task editTask(Long task_id);
    List<Task> getTaskByStatus(Status taskStatus);
    Task getTaskByTitle();
    List<TaskDto> getTasksByProjectId(Long projectId);

    List<Long> getTasksId(List<Task> tasks);
}
