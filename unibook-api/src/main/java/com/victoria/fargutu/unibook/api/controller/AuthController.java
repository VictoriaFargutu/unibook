package com.victoria.fargutu.unibook.api.controller;

import com.victoria.fargutu.unibook.repository.model.AuthSession;
import com.victoria.fargutu.unibook.repository.model.User;
import com.victoria.fargutu.unibook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public User validateToken(@RequestHeader("X-Auth-Token") String authToken) {
        return authService.validateToken(authToken);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public AuthSession login(@RequestHeader("Authorization") String authToken) {
        return authService.verifyCredentials(authToken);
    }
}
