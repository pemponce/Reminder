package com.example.reminderproject.service.impl;

import com.example.reminderproject.model.Tag;
import com.example.reminderproject.repository.TagRepository;
import com.example.reminderproject.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

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
}
