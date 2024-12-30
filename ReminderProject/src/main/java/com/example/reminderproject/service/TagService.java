package com.example.reminderproject.service;

import com.example.reminderproject.model.Tag;

import java.util.List;

public interface TagService {
    void save(Tag tag);
    void saveAll(List<Tag> tags);
    void delete(Tag tag);
    void edit(Tag tag);
}
