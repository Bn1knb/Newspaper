package com.github.newspaper.security.service;

import com.github.newspaper.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean isEnabled(User user);

    Enum getPrivilege(User user) ;

    void setPrivilege(User user, Enum privilege);

    void save(User user);

    User findByUsername(String userName);

    User findUserByEmail(String email);
}
