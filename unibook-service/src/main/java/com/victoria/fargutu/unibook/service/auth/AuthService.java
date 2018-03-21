package com.victoria.fargutu.unibook.service.auth;

import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.user.User;
import org.springframework.scheduling.annotation.Scheduled;

public interface AuthService {
    AuthSession createAuthSession(User user);

    AuthSession verifyCredentials(String token);

    AuthSession validateToken(String token);

    @Scheduled
    void invalidateSession();

    void logout();
}
