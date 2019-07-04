package com.github.newspaper.dao;

import com.github.newspaper.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    Optional<User> findUserByUsername(String userName);

    User findUserById(Long id);

    boolean IsEnabled(User user);

    List<User> findAll();

    @Query(value = "SELECT u.* FROM user u ORDER BY u.created_at DESC", nativeQuery = true)
    List<User> findAllbyOrderByCreatedAtDesc();
}
