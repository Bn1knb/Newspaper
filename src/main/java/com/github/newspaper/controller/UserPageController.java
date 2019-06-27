package com.github.newspaper.controller;

import com.github.newspaper.entity.Post;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.PostService;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class UserPageController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        User loggedinUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", loggedinUser);

        List<Post> userPosts = loggedinUser.getPosts();

        if (userPosts == null) {

            model.addAttribute("ErrorMessage", "Sorry no posts yet");

            return "user";
        }

        model.addAttribute("allPosts", userPosts);
        return "user";
    }

    @RequestMapping("/posts/delete/{id}")
    public String view(@PathVariable("id") Long id) {


        postService.delete(id);
        return "redirect:/user";
    }
}