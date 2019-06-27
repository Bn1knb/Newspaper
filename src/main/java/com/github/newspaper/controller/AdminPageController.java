package com.github.newspaper.controller;

import com.github.newspaper.entity.User;
import com.github.newspaper.security.Role;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        User loggedinUser = userService.findByUsername(principal.getName());
        List<User> allUsers = userService.findAll();
        String role = "USER";
        //List<String> roles = Arra(Role.values().toString());

        if (allUsers == null) {

            model.addAttribute("ErrorMessage", "Sorry no users yet");

            return "admin";
        }

        //model.addAttribute("roles", roles);
        model.addAttribute("role", role);
        model.addAttribute("user", loggedinUser);
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/admin", params = {"approve"})
    public String approve(@ModelAttribute("allUsers") List<User> selected, Model model, Principal principal) {

        if (selected != null) {
            for (User user : selected) {
                userService.enable(user);
            }
        } else {
            model.addAttribute("Error", "No user selected");
        }
        User loggedinUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", loggedinUser);
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);

        return "admin";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin", params = {"setRoles"})
    public String setRole(@ModelAttribute("allUsers") List<User> selected, Model model, Principal principal) {

        if (selected != null) {
            for (User user : selected) {

            }
        } else {
            model.addAttribute("Error", "No user selected");
        }

        User loggedinUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", loggedinUser);
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);

        return "admin";
    }*/
}
