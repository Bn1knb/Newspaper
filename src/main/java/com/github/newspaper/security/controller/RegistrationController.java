package com.github.newspaper.security.controller;

import com.github.newspaper.dao.UserRepository;
import com.github.newspaper.entity.User;
import com.github.newspaper.security.service.SecurityService;
import com.github.newspaper.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @GetMapping
    public String showSignForm(Model model) {

        model.addAttribute("userForm", new User());  ///TODO add DTO and interact with it instead of user object
        return "signup";
    }

    @PostMapping
    public String signup(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userRepository.save(user);
        securityService.autoLogin(user.getUserName(), user.getPassword());

        return "redirect:index";
    }
}
