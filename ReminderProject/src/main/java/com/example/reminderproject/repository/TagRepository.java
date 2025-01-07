package com.example.reminderproject.repository;

import com.example.reminderproject.model.Project;
import com.example.reminderproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> getTagsByProject(Project project);
}
