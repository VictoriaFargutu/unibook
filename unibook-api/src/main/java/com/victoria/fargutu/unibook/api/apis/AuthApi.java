package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.repository.model.auth.AuthSessionResponse;
import com.victoria.fargutu.unibook.repository.model.user.UserResponse;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
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
    private UserService userService;

    @Autowired
    AuthApi(AuthService authService, AuthManager authManager, UserService userService) {
        this.authService = authService;
        this.authManager = authManager;
        this.userService = userService;
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public AuthSessionResponse validateToken() {
        UserResponse user = new UserResponse(authManager.getLoggedInUser());
        return new AuthSessionResponse(authManager.getAuthSession().getSessionToken(), user);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public AuthSessionResponse login(@RequestHeader("Authorization") String authToken) {
        AuthSession authSession = authService.verifyCredentials(authToken);
        UserResponse user = new UserResponse(userService.getUserById(authSession.getUserId()));
        return new AuthSessionResponse(authSession.getSessionToken(), user);
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout() {
        authService.logout();
    }
}
