package com.example.reminderproject.repository;

import com.example.reminderproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
