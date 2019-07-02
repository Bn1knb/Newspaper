package com.github.newspaper.controller;

import com.github.newspaper.model.Post;
import com.github.newspaper.model.User;
import com.github.newspaper.service.PostService;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String view(Model model, Principal principal) {

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
    public String delete(@PathVariable("id") Long id) {


        postService.delete(id);
        return "redirect:/user";
    }

    @RequestMapping("/users/{username}")
    public String view(@PathVariable("username") String username, Model model, Principal principal) {

        User user = userService.findByUsername(username);
        User currenUser = userService.findByUsername(principal.getName());
        List<Post> allPosts = postService.findAllPostsOfUser(user);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("currentUser", currenUser);
        model.addAttribute("roleAdmin", "ADMIN");
        model.addAttribute("roleModerator", "MODERATOR");
        model.addAttribute("allPosts", allPosts);
        model.addAttribute("user", user);
        return "users";
    }
}