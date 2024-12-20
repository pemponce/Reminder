package com.example.reminderproject.dto;

import com.example.reminderproject.model.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;

    private Role role;
}
