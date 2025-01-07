package com.example.reminderproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    private String tagName;
    private String color;

    @Override
    public String toString() {
        return "{id=" + id + ",\n" +
                " project='" + project.getProjectName() + "',\n" +
                " tagName='" + tagName + "',\n" +
                " color='" + color + "'}";
    }
}