package com.github.newspaper.controller;

import com.github.newspaper.entity.Post;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.PostService;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostsController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {

        Post post = this.postService.findById(id);
        if (post == null) {
            return "redirect:/";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    @RequestMapping("/post/create")
    public ModelAndView create(Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject(currentUser);
        modelAndView.addObject(post);
        modelAndView.setViewName("posts/create");
        return modelAndView;
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid Post post, BindingResult bindingResult, Model model, Principal principal) {


        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", currentUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts/create");

        if (post.getHeadLine().isEmpty()) {
            bindingResult.rejectValue("title", "error.post", "Title cannot be empty");
        }
        if (post.getContent().isEmpty()) {
            bindingResult.rejectValue("body", "error.post", "Content cannot be empty");
        }

        if (!bindingResult.hasErrors()) {
            post.setUser(currentUser);
            this.postService.post(post);
            modelAndView.addObject("successMessage", "Post has been created");
            modelAndView.addObject("post", new Post());
        }
        return modelAndView;
    }
}
