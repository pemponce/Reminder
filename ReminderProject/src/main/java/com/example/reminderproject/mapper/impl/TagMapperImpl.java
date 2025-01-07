package com.example.reminderproject.mapper.impl;

import com.example.reminderproject.dto.TagDto;
import com.example.reminderproject.mapper.TagMapper;
import com.example.reminderproject.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapperImpl implements TagMapper {
    @Override
    public TagDto toTagDto(Tag tag) {

        if (tag != null) {
            return TagDto.builder()
                    .projectName(tag.getProject().getProjectName())
                    .tagName(tag.getTagName())
                    .tagColor(tag.getColor())
                    .build();
        }
        return null;
    }
}
