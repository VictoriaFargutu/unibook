package com.victoria.fargutu.unibook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @GetMapping
    public String helloWorld() {
        return "index/index";
    }
}
