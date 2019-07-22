package com.github.bn1knb.newspaper.controller;

import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, Principal principal, Authentication authentication) {

        model.addAttribute("auth", authentication);
        if (authentication != null) {
            model.addAttribute("user", userService.findByUsername(principal.getName()));
        }
        return "index";
    }
}
