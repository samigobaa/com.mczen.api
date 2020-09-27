package com.mczen.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class LoginEvent {
    private String User;
    private LoginEventType Action;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateTime;


    public LoginEvent(String user, LoginEventType action, LocalDateTime dateTime) {
        User = user;
        Action = action;
        this.dateTime = dateTime;
    }

    public String getUser() {
        return User;
    }

    public LoginEventType getAction() {
        return Action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
