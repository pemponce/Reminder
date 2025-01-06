package com.example.reminderproject.dto;

import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDto {

    @Schema(title = "Заголовок")
    @NotBlank
    private String title;

    @Schema(title = "Описание")
    @Lob
    private String content;
    private String attachmentPath = "ReminderProject/data/";
    private String author;
    private Project project;
    @Schema(title = "Статус задачи")
    private Status status;

    @Schema(title = "Список тегов (ID)")
    private List<Long> tagIds;
}
