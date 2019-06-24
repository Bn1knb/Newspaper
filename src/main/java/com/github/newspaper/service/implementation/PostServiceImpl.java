package com.github.newspaper.service.implementation;

import com.github.newspaper.dao.PostRepository;
import com.github.newspaper.entity.Post;
import com.github.newspaper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void post(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(String headline) {

    }

    @Override
    public void change(String headline) {

    }

    @Override
    public Iterable<Post> getPostsOrderBYDate() {

        return null;
    }

    @Override
    public Iterable<Post> getPostsOrderByComments() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
