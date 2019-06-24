package com.github.newspaper.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/feed")
public class FeedController {

    @GetMapping
    public String feed() {
        return "feed";
    }

    @PostMapping
    public String feed(Principal principal) {


        return "feed";
    }
}
