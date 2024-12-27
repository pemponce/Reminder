package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.model.Status;
import com.example.reminderproject.model.Task;
import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.TaskRepository;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public void createTask(TaskDto taskDto) {
        var user = userService.getCurrentUser();
        taskDto.setAuthor(user.getUsername());

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .status(taskDto.getStatus())
                .attachmentPath(taskDto.getAttachmentPath())
                .content(taskDto.getContent())
                .author(taskDto.getAuthor())
                .project_id(taskDto.getProject_id())
                .build();
        taskRepository.save(task);
    }

    @Override
    public Task editTask(Long task_id) {
        var task = taskRepository.getTaskById(task_id);
        task.setContent(task.getContent() + "");
        return null;
    }

    @Override
    public List<Task> getTaskByStatus(Status taskStatus) {
        return null;
    }

    @Override
    public Task getTaskByTitle() {
        return null;
    }

    @Override
    public List<Task> getByProjectId(Long projectId) {
        return null;
    }
}