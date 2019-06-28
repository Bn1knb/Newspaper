package com.github.newspaper.controller;

import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model, Principal principal) {

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "index";
    }
}
