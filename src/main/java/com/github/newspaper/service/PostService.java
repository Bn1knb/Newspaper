package com.github.newspaper.service;

import com.github.newspaper.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void post(Post post);

    void delete(String headline);

    void change(String headline);

    Iterable<Post> getPostsOrderBYDate();

    Iterable<Post> getPostsOrderByComments();

    Post findById(Long id);

    List<Post> findLatest5();
}
