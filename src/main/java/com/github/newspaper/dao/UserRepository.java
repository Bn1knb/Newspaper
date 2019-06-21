package com.github.newspaper.dao;

import com.github.newspaper.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email) ;
    User findUserByUsername(String userName);
    boolean IsEnabled(User user);
}
