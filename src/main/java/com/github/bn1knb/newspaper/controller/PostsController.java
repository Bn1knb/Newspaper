package com.github.bn1knb.newspaper.controller;

import com.github.bn1knb.newspaper.model.Post;
import com.github.bn1knb.newspaper.model.User;
import com.github.bn1knb.newspaper.service.PostService;
import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostsController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model, Principal principal) {

        Post post = this.postService.findById(id);
        if (post == null) {
            return "redirect:/";
        }
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("post", post);
        return "posts/view";
    }

    @RequestMapping("/posts/create")
    public ModelAndView create(Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());
        ModelAndView modelAndView = new ModelAndView();

        if (!currentUser.isEnabled()) {
            modelAndView.setViewName("redirect:errors/userNotApprovedYet");
            return modelAndView;
        }

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

        if (!bindingResult.hasErrors()) {
            post.setUser(currentUser);
            postService.post(post);
            modelAndView.addObject("successMessage", "Post has been created");
            modelAndView.addObject("post", new Post());
        }
        return modelAndView;
    }
}
