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
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto/*, BindingResult bindingResult, Model model*/) {

       /* User existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null){

            model.addAttribute("emailError", "Email exists");
            return "register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }*/
        System.out.println("saving the user");
        userService.save(userDto);

        return "redirect:login";
    }
}
