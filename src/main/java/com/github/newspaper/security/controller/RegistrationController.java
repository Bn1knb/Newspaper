package com.github.newspaper.security.controller;

import com.github.newspaper.entity.User;
import com.github.newspaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String showSignForm(User user) {
        return ""; //TODO show design form
    }

    @PostMapping
    public String signup(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ""; //TODO go back to re enter
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
}
