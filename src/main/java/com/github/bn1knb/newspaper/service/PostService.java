package com.github.bn1knb.newspaper.service;

import com.github.bn1knb.newspaper.model.Post;
import com.github.bn1knb.newspaper.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void post(Post post);

    void approve(Post post);

    void delete(Long id);

    void change(String headline);


    Iterable<Post> getPostsOrderByComments();

    List<Post> findAllPostsOfUser(User user);

    List<Post> findAllOrderByDate();

    Post findById(Long id);

    List<Post> findLatest5();
}
