package com.victoria.fargutu.unibook.repository.model;

import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthManager {
    private User loggedInUser;
    private AuthSession authSession;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public AuthSession getAuthSession() {
        return authSession;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setAuthSession(AuthSession authSession) {
        this.authSession = authSession;
    }
}
