package com.github.bn1knb.newspaper.security.controller;

import com.github.bn1knb.newspaper.dto.UserRegistrationDto;
import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerForm(Model model, Authentication authentication) {

        if (authentication != null) {
            return "redirect:feed";
        }

        model.addAttribute("userDto", new UserRegistrationDto());
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userDto") @Valid UserRegistrationDto userDto,
                           BindingResult bindingResult,
                           HttpServletRequest request) throws ServletException {

        //User existing = userService.findUserByEmail(userDto.getEmail());

        /*if (existing != null) {

            model.addAttribute("emailError", "user with this email already exists");
            return "register";
        }*/
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userDto);
        request.login(userDto.getUsername(), userDto.getPassword());

        return "redirect:/feed";
    }
}
