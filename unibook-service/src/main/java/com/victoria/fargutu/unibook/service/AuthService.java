package com.victoria.fargutu.unibook.service;

import com.victoria.fargutu.unibook.repository.model.AuthSession;
import com.victoria.fargutu.unibook.repository.model.User;
import org.springframework.scheduling.annotation.Scheduled;

public interface AuthService {
    AuthSession createAuthSession(User user);

    AuthSession getAuthSessionByToken(String token);

    void deleteSessionById(Long id);

    AuthSession verifyCredentials(String token);

    User validateToken(String token);

    @Scheduled
    void invalidateSession();
}
