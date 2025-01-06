package com.example.reminderproject;

import com.example.reminderproject.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReminderProjectApplication {

    public static void main(String[] args) {
        SecurityConfig.loadEnv();
        SpringApplication.run(ReminderProjectApplication.class, args);
    }

}


/*
    TODO: пофиксить. Пользователь может создать от имени другого пользователя проект.
     Пофиксить создание таска, чтоб можно было использовать только те теги
      которые есть внутри параметров конкретного проекта
*/