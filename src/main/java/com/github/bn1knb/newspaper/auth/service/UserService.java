package com.github.bn1knb.newspaper.auth.service;

import com.github.bn1knb.newspaper.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String userName);
}
