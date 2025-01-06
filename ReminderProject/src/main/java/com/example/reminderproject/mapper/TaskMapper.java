package com.example.reminderproject.mapper;

import com.example.reminderproject.dto.TaskDto;
import com.example.reminderproject.model.Task;

public interface TaskMapper {
    TaskDto toTaskDto(Task task);

}
