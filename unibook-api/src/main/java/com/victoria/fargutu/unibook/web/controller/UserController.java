package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.db.UserRepository;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class UserController {
    private AuthManager authManager;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    UserController(AuthManager authManager, UserRepository userRepository, UserService userService) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @HasRole(UserRole.USER)
    @GetMapping
    public String openIndexPage(ModelMap modelMap) {
        User user = authManager.getLoggedInUser();
        modelMap.put("loggedUser", user);
        return "index/index";
    }
}
