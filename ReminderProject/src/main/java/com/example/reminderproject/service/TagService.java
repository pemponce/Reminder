package com.example.reminderproject.service;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.model.Task;

import java.util.List;

public interface TagService {
    void save(Tag tag);
    void saveAll(List<Tag> tags);
    void delete(Tag tag);
    void edit(Tag tag);
    List<Tag> getAllAllowedProjectTags(Project project);
    List<Long> getAllAllowedProjectTagsId(Project project);
    List<Tag> getAllTaskTags(Task taskId);
}
