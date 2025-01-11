package com.example.reminderproject.mapper.impl;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.model.Task;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final TagService tagService;
    private final TagRepository tagRepository;
    private final UserService userService;

    @Override
    public TaskDto toTaskDto(Task task) {
        if (task != null) {
            List<Long> ids = new ArrayList<>();
            for (Tag tag:task.getTags()) {
                ids.add(tag.getId());
            }

            return TaskDto.builder()
                    .title(task.getTitle())
                    .content(task.getContent())
                    .tagIds(ids)
                    .attachmentPath(task.getAttachmentPath())
                    .project(task.getProject())
                    .author(task.getAuthor())
                    .status(task.getStatus())
                .build();
        }
        return null;
    }

    @Override
    public void apply(Task task, TaskDto taskDto) {
        List<Long> allowedTags = tagService.getAllAllowedProjectTagsId(task.getProject());

        List<Long> requestedTagIds = taskDto.getTagIds();

        List<Long> invalidTagIds = requestedTagIds.stream()
                .filter(tagId -> !allowedTags.contains(tagId))
                .toList();

        if (!invalidTagIds.isEmpty()) {
            throw new IllegalArgumentException("Недопустимые теги для проекта: " + invalidTagIds);
        }

        List<Tag> tags = tagRepository.findAllById(requestedTagIds);

        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        task.setStatus(taskDto.getStatus());
        task.setTags(tags);
     }
}
