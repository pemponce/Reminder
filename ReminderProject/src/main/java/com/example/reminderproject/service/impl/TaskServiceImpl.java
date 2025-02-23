package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.exception.UserNotAllowedToThisProjectException;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.model.*;
import com.example.reminderproject.repository.ProjectUsersRepository;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.repository.TaskRepository;
import com.example.reminderproject.service.ProjectUsersService;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.TaskService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TagService tagService;
    private final UserService userService;
    private final TagRepository tagRepository;
    private final TaskMapper taskMapper;
    private final ProjectUsersRepository projectUsersRepository;
    private final ProjectUsersService projectUsersService;

    @Override
    @Transactional
    public void createTask(TaskDto taskDto) {
        var user = userService.getCurrentUser();
        taskDto.setAuthor(user.getUsername());

        List<Long> allowedTags = tagService.getAllAllowedProjectTagsId(taskDto.getProject());

        List<Long> requestedTagIds = taskDto.getTagIds();
        List<Long> invalidTagIds = requestedTagIds.stream().filter(tagId -> !allowedTags.contains(tagId)).toList();

        if (!invalidTagIds.isEmpty()) {
            throw new IllegalArgumentException("Недопустимые теги для проекта: " + invalidTagIds);
        }

        List<Tag> tags = tagRepository.findAllById(requestedTagIds);

        Task task = Task.builder().title(taskDto.getTitle()).status(taskDto.getStatus()).attachmentPath(taskDto.getAttachmentPath()).content(taskDto.getContent()).author(taskDto.getAuthor()).project(taskDto.getProject()).tags(tags).deadline(taskDto.getDeadline()).build();


        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public void editTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void checkIfTasksTimeOver() {
        List<Task> expiredTasks = taskRepository.findAll().stream().filter(Task::isTimeOver).peek(task -> task.setStatus(Status.TIME_IS_OVER)).toList();

        taskRepository.saveAll(expiredTasks);
    }

    @Override
    public List<TaskDto> getTasksByStatus(Status taskStatus) {

        List<TaskDto> taskDtos = new ArrayList<>(taskRepository.findTasksByStatus(taskStatus)
                .stream()
                .map(taskMapper::toTaskDto)
                .toList());

        List<ProjectUsers> projs = projectUsersService.getProjUsersByUser(userService.getCurrentUser().getId());

        for (ProjectUsers p: projs) {
            taskDtos.removeIf(t -> p.getProjectId().longValue() != t.getProject().getId().longValue());
        }

        int i = 0;
        return taskDtos;
    }

    @Override
    public Task getTasksByTitle(String title) {
        return null;
    }

    @Override
    public List<TaskDto> getTasksByProjectId(Long projectId) {
        var currUser = userService.getCurrentUser();
        List<TaskDto> res = new ArrayList<>();
        if (projectUsersRepository.getProjectUsersByUserIdAndProjectId(currUser.getId(), projectId) != null) {

            for (Task task : taskRepository.getTasksByProject_id(projectId)) {
                res.add(taskMapper.toTaskDto(task));
            }
        } else {
            throw new UserNotAllowedToThisProjectException(currUser.getUsername());
        }


        return res;
    }

    @Override
    public List<Long> getTasksId(List<Task> tasks) {
        List<Long> ids = new ArrayList<>();

        for (Task task : tasks) {
            ids.add(task.getId());
        }
        return ids;
    }
}