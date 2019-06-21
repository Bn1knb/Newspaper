package com.github.newspaper.security.controller;

import com.github.newspaper.dto.UserRegistrationDto;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.UserService;
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
    UserService userService;

    @GetMapping
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute @Valid UserRegistrationDto userDto, BindingResult bindingResult) {

        User existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null){
            return "signup";
            //TODO add email dup warning
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.save(userDto);

        return "redirect:/signin";
    }
}
