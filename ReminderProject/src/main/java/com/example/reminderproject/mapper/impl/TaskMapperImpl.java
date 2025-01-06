package com.example.reminderproject.mapper.impl;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.mapper.TaskMapper;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {
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
}
