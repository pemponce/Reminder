package com.example.reminderproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long project_id;
    private String title;
    @Lob
    private String content;
    private String attachmentPath;
    private String author;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Tag> tags;
}
