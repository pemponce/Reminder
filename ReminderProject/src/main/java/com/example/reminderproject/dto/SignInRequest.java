package com.example.reminderproject.dto;

import lombok.Data;

@Data
public class SignInRequest {
    String password;
    String email;
}
