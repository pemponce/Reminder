package com.example.reminderproject.mapper;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.model.Tag;

public interface TagMapper {
    TagDto toTagDto(Tag tag);
}
