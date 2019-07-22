package com.github.bn1knb.newspaper.controller;

import com.github.bn1knb.newspaper.model.Post;
import com.github.bn1knb.newspaper.model.User;
import com.github.bn1knb.newspaper.service.PostService;
import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public FeedController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping("/feed")
    public String feed(Model model, Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());

        model.addAttribute("user", currentUser);

        List<Post> latest5Posts = postService.findLatest5();

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
