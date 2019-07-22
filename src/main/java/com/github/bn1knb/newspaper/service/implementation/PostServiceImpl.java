package com.github.bn1knb.newspaper.service.implementation;

import com.github.bn1knb.newspaper.dao.PostRepository;
import com.github.bn1knb.newspaper.model.Post;
import com.github.bn1knb.newspaper.model.User;
import com.github.bn1knb.newspaper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public void approve(Post post) {
        post.setApproved(true);
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(postRepository.findPostById(id));
    }

    @Override
    public void change(String headline) {

    }

    @Override
    public Iterable<Post> getPostsOrderByComments() {
        return null;
    }

    @Override
    public List<Post> findAllPostsOfUser(User user) {
        return postRepository.findAllByUserOrderByDateDesc(user);
    }

    @Override
    public List<Post> findAllOrderByDate() {
        return postRepository.findAllbyOrderByDateDesc();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findLatest5() {
        return postRepository.findLatest5ApprovedPosts(PageRequest.of(0, 10));
    }
}
