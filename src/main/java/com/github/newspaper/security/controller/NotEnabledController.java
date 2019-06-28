package com.github.newspaper.security.controller;

import com.github.newspaper.entity.User;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class NotEnabledController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/errors/userNotApprovedYet", method = RequestMethod.GET)
    public String notEnabled(Model model, Principal principal) {
        User loggedinUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", loggedinUser);

        return "errors/userNotApprovedYet";
    }
}
