package com.github.newspaper.controller;

import com.github.newspaper.model.User;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminPageController {

    private final UserService userService;

    @Autowired
    public AdminPageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        User loggedinUser = userService.findByUsername(principal.getName());
        List<User> allUsers = userService.findAll();

        if (allUsers == null) {

            model.addAttribute("ErrorMessage", "Sorry no users");

            return "admin";
        }

        model.addAttribute("user", loggedinUser);
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

    @RequestMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        userService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping("/users/enable/{id}")
    public String enable(@PathVariable("id") Long id) {

        userService.enable(userService.findById(id));
        return "redirect:/admin";
    }

    @RequestMapping("/users/makeadmin/{username}")
    public String setAsAdmin(@PathVariable("username") String username) {
        userService.setRole(userService.findByUsername(username), "ADMIN");
        return "redirect:/users/view/" + username;
    }

    @RequestMapping("/users/makemoderator/{username}")
    public String setAsModerator(@PathVariable("username") String username) {
        userService.setRole(userService.findByUsername(username), "MODERATOR");
        return "redirect:/users/view/" + username;
    }
}