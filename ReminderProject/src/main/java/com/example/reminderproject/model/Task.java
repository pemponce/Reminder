package com.example.reminderproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String attachmentPath;
    private String author;

    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime deadline;


    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @PrePersist
    @PreUpdate
    private void roundDeadline() {
        if (deadline != null) {
            this.deadline = deadline.withSecond(0).withNano(0);
        }
    }

    public boolean isTimeOver() {
        return (!status.equals(Status.DONE)) && LocalDateTime.now().isAfter(deadline);
    }
}