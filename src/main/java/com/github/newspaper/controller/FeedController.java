package com.github.newspaper.controller;

import com.github.newspaper.entity.Post;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.PostService;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping("/feed")
    public String feed(Model model, Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());

        model.addAttribute("user", currentUser);

        List<Post> latest5Posts = this.postService.findLatest5();

        if (latest5Posts == null) {

            model.addAttribute("ErrorMessage", "Sorry no posts yet");

            return "feed";
        }

        model.addAttribute("latest5Posts", latest5Posts);
        List<Post> latest3Posts = latest5Posts.stream().limit(3).collect(Collectors.toList());
        model.addAttribute("latest3Posts", latest3Posts);

        return "feed";
    }

}
