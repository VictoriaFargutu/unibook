package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.model.auth.AuthSession;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.web.FlashMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/authorization")
public class AuthController {

    private AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "/login")
    public String openLoginPage(@ModelAttribute("notification") FlashMessage notification, ModelMap modelMap) {
        if (notification != null) {
            modelMap.addAttribute("error", notification.getMessage());
        }
        return "/authentication/login";
    }

    @PostMapping(path = "/login")
    public String login(@Valid @RequestParam("Authorization") String authToken, HttpServletResponse response) {
        AuthSession authSession = authService.verifyCredentials(authToken);
        Cookie cookie = new Cookie("X-Auth-Token", authSession.getSessionToken());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
