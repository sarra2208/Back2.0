package com.example.test.domain.model;

import com.example.test.infrastructure.persistence.entity.UserAppEntity;

public class AuthResponse {
    private String token;
    private UserAppEntity user;

    public AuthResponse(String token, UserAppEntity user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserAppEntity getUser() {
        return user;
    }

    public void setUser(UserAppEntity user) {
        this.user = user;
    }
}

