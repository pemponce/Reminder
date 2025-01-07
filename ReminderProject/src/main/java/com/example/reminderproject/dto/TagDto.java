package com.example.reminderproject.dto;

import com.example.reminderproject.model.Project;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDto {
        private String tagName;
        private String tagColor;
}
