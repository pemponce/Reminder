package com.example.reminderproject.service.impl;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.mapper.TagMapper;
import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.Tag;
import com.example.reminderproject.model.Task;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.service.TagService;
import com.example.reminderproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void saveAll(List<Tag> tags) {
        tagRepository.saveAll(tags);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public void edit(Tag tag) {
        tagRepository.save(Tag.builder()
                .tagName(tag.getTagName())
                .color(tag.getColor())
                .project(tag.getProject())
                .build());
    }

    @Override
    public List<Tag> getAllAllowedProjectTags(Project project) {
        return tagRepository.getTagsByProject(project);
    }

    @Override
    public List<Long> getAllAllowedProjectTagsId(Project project) {
        List<Long> tagsId = new ArrayList<>();

        for (Tag tag: tagRepository.getTagsByProject(project)) {
            tagsId.add(tag.getId());
        }

        return tagsId;
    }

    @Override
    public List<Tag> getAllTaskTags(Task task) {

        List<Long> tagsIds = task.getTags().stream().map(Tag::getId).toList();

        List<Tag> result = new ArrayList<>();
        for (Long id:tagsIds) {
            result.add(tagRepository.getTagById(id));
        }

        return result;
    }
}
