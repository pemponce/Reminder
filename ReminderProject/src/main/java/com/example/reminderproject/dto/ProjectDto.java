package com.example.reminderproject.dto;

import com.example.reminderproject.model.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    private String projectName;
    private Long userId;
    private List<Tag> tags;
}
