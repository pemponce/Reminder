package com.example.reminderproject.model;

public enum Status {
    TODO("TODO"),
    IN_PROCESS("IN_PROCESS"),
    DONE("DONE"),
    TIME_IS_OVER("TIME_IS_OVER");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
