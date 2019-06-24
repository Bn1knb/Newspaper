package com.github.newspaper.dao;

import com.github.newspaper.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Override
    Iterable<Post> findAll();

    Iterable<Post> findAllOrderByCreatedAt();

    Iterable<Post> findAllOrderByComments();
}
