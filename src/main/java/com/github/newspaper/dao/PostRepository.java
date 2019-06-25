package com.github.newspaper.dao;

import com.github.newspaper.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Override
    Iterable<Post> findAll();

    //Iterable<Post> findAllOrderByDate();

    //Iterable<Post> findAllOrderByComments(); //TODO idk this need to be done smhow

    @Query(value = "SELECT p.* FROM post p ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findLates5Posts(Pageable pageable);
}
