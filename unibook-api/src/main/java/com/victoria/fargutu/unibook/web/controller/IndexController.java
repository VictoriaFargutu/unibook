package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private AuthManager authManager;

    public IndexController(AuthManager authManager) {
        this.authManager = authManager;
    }

    @HasRole(UserRole.ADMIN)
    @GetMapping
    public String openIndexPage(ModelMap modelMap) {
        modelMap.put("userRole", authManager.getLoggedInUser().getRole().name());
        return "index/index";
    }
}
