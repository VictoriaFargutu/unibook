package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.auth.AuthManager;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSessionResponse;
import com.victoria.fargutu.unibook.repository.model.user.UserResponse;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthApi {
    private AuthService authService;
    private AuthManager authManager;

    @Autowired
    AuthApi(AuthService authService, AuthManager authManager) {
        this.authService = authService;
        this.authManager = authManager;
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public AuthSessionResponse validateToken() {
        UserResponse user = new UserResponse(authManager.getLoggedInUser());
        return new AuthSessionResponse(authManager.getAuthSession().getSessionToken(), user);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public AuthSession login(@RequestHeader("Authorization") String authToken) {
        return authService.verifyCredentials(authToken);
    }
}
