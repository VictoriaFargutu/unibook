package com.victoria.fargutu.unibook.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AuthSession {

    @Id
    @GeneratedValue
    private Long id;

    private String sessionToken;
    private Long expirationTime;
    private Long userId;

    @Transient
    private User user;

    public Long getId() {
        return id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
