package com.example.reminderproject.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "friend_id", nullable = false)
    private Long friendId;

    private boolean requestAccepted;
}
