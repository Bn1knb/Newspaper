package com.github.bn1knb.newspaper.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Authentication authentication) {

        if (authentication != null) {
            return "redirect:feed";
        }
        return "login";
    }
}
