package com.github.newspaper.security.controller;

import com.github.newspaper.dto.UserRegistrationDto;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("userDto", new UserRegistrationDto());
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                           BindingResult bindingResult, Model model) {

        User existing = userService.findUserByEmail(userDto.getEmail());

        if (existing != null) {

            model.addAttribute("emailError", "Email exists");
            return "register";
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // TODO you forgot to add role for user!
        userService.save(userDto);

        return "redirect:login";
    }
}
