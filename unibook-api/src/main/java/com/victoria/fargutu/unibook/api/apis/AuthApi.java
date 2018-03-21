package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthApi {
    private AuthService authService;

    @Autowired
    AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public AuthSession validateToken(@RequestHeader("X-Auth-Token") String authToken) {
        return authService.validateToken(authToken);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public AuthSession login(@RequestHeader("Authorization") String authToken) {
        return authService.verifyCredentials(authToken);
    }
}
