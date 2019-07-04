package com.github.newspaper.security.controller;

import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class AccessDeniedController {

    private final UserService userService;

    @Autowired
    public AccessDeniedController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            model.addAttribute("user", userService.findByUsername(principal.getName()));
        }

        return "403Page";
    }
}
