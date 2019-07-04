package com.github.newspaper.dao;

import com.github.newspaper.model.Post;
import com.github.newspaper.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "SELECT p.* FROM post p ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findAllbyOrderByDateDesc();

    List<Post> findAllByUserOrderByDateDesc(User user);

    Post findPostById(Long id);

    //Iterable<Post> findAllOrderByDate();

    //Iterable<Post> findAllOrderByComments(); //TODO idk this need to be done smhow

    @Query(value = "SELECT p.* FROM post p WHERE p.is_approved = TRUE ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findLatest5ApprovedPosts(Pageable pageable);
}
