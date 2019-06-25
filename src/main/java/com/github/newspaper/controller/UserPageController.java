package com.github.newspaper.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserPageController {

    @GetMapping
    public String index(Model model, Principal principal) {

        User loggedinUser = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user", loggedinUser);
        return "user";
    }
}
