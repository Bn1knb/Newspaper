package com.github.newspaper.controller;

import com.github.newspaper.entity.Post;
import com.github.newspaper.entity.User;
import com.github.newspaper.service.PostService;
import com.github.newspaper.service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<Post> userPosts = postService.findAllPostsOfUser(loggedinUser);

        if (userPosts == null) {

            model.addAttribute("ErrorMessage", "Sorry no posts yet");

            return "user";
        }
        model.addAttribute("user", loggedinUser);
        model.addAttribute("posts", userPosts);
        return "user";
    }

    //TODO add validation to delete button without boxes being checked
    //TODO add current user to form then delete ? in  <h2>Welcome: <span th:utext="${user?.username}"></span></h2>
    @RequestMapping(value = "/user", params = {"delete"})
    public String delete(@ModelAttribute("posts")List<Post> selected) {

        if (selected != null) {
            for (Post post : selected) {
                postService.delete(post.getId());
            }
        }

        return "user";
    }
}