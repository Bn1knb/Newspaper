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
public class ModeratorController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public ModeratorController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/moderator", method = RequestMethod.GET)
    public String view(Model model, Principal principal) {
        User loggedinUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", loggedinUser);

        List<Post> userPosts = postService.findAllOrderByDate();

        if (userPosts == null) {

            model.addAttribute("ErrorMessage", "Sorry no posts yet");

            return "user";
        }
        model.addAttribute("allPosts", userPosts);

        return "moderator";
    }

    @RequestMapping("/post/approve/{id}")
    public String approve(@PathVariable("id") Long id) {


        postService.approve(postService.findById(id));
        return "redirect:/moderator";
    }

    @RequestMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        postService.delete(id);
        return "redirect:/moderator";
    }
}
