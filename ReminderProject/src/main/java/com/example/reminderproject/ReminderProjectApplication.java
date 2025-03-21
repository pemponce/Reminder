package com.example.reminderproject;

import com.example.reminderproject.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ReminderProjectApplication {

    public static void main(String[] args) {
        SecurityConfig.loadEnv();
        SpringApplication.run(ReminderProjectApplication.class, args);
    }

}


/*
    TODO: Управление ролями для AUTHOR т.е. автор может назначать кого то CMD а кого то например понизить с должности CMD на CREWMATE
*/