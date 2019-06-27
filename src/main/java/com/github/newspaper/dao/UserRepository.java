package com.github.newspaper.dao;

import com.github.newspaper.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserByUsername(String userName);

    User findUserById(Long id);

    boolean IsEnabled(User user);

    List<User> findAll();
}
