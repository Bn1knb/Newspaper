package com.github.newspaper;

import com.github.newspaper.entity.Comment;
import com.github.newspaper.entity.Post;
import com.github.newspaper.entity.User;
import com.github.newspaper.repository.CommentRepository;
import com.github.newspaper.repository.PostRepository;
import com.github.newspaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

public class CommanLineTest implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Anton");
        user.setEmail("Email@example.com");
        user.setPassword("password");

        Post post = new Post();
        post.setHeadLine("headline");
        post.setContent("content");

        Comment comment = new Comment();
        comment.setContent("comment content");

        List<Comment> comm = new ArrayList<>();
        comm.add(comment);
        post.setComments(comm);

        List<Post> posts  = new ArrayList<>();
        posts.add(post);

        user.setPosts(posts);

        repository.save(comment);
        postRepository.save(post);
        userRepository.save(user);
    }
}
