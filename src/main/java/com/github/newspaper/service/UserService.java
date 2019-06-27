package com.github.newspaper.service;

import com.github.newspaper.dto.UserRegistrationDto;
import com.github.newspaper.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    boolean isEnabled(User user);

    void addRole(User user, String role);

    void save(UserRegistrationDto userDto);

    User findByUsername(String userName);

    User findUserByEmail(String email);

    void enable(User user);

    void disable(User user);

    List<User> findAll();

    void delete(Long id);
}
