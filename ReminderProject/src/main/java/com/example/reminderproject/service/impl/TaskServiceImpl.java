package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.model.Status;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.model.Task;
import com.example.reminderproject.model.User;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.repository.TaskRepository;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TagService tagService;
    private final UserService userService;
    private final TagRepository tagRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public void createTask(TaskDto taskDto) {
        var user = userService.getCurrentUser();
        taskDto.setAuthor(user.getUsername());

        List<Long> allowedTags = tagService.getAllAllowedProjectTagsId(taskDto.getProject());

        List<Long> requestedTagIds = taskDto.getTagIds();
        List<Long> invalidTagIds = requestedTagIds.stream()
                .filter(tagId -> !allowedTags.contains(tagId))
                .toList();

        if (!invalidTagIds.isEmpty()) {
            throw new IllegalArgumentException("Недопустимые теги для проекта: " + invalidTagIds);
        }

        List<Tag> tags = tagRepository.findAllById(requestedTagIds);

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .status(taskDto.getStatus())
                .attachmentPath(taskDto.getAttachmentPath())
                .content(taskDto.getContent())
                .author(taskDto.getAuthor())
                .project(taskDto.getProject())
                .tags(tags)
                .build();


        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
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
    public List<TaskDto> getTasksByProjectId(Long projectId) {
        List<TaskDto> res = new ArrayList<>();
        int i = 0;

        for (Task task: taskRepository.getTasksByProject_id(projectId)) {
             res.add(taskMapper.toTaskDto(task));
             i++;
        }

        System.out.println(i);
        return res;
    }

    @Override
    public List<Long> getTasksId(List<Task> tasks) {
        List<Long> ids = new ArrayList<>();

        for (Task task:tasks) {
            ids.add(task.getId());
        }
        return ids;
    }
}