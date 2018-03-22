package com.victoria.fargutu.unibook.repository.model.auth;

import com.victoria.fargutu.unibook.repository.model.user.UserResponse;

public class AuthSessionResponse {
    private String sessionToken;
    private UserResponse user;

    public AuthSessionResponse(String sessionToken, UserResponse user) {
        this.sessionToken = sessionToken;
        this.user = user;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
