package com.github.newspaper.security.service.implementation;

import com.github.newspaper.dao.UserRepository;
import com.github.newspaper.entity.User;
import com.github.newspaper.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
                                                                                    //TODO add getters for data and other stuff
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean isEnabled(User user) {
        return userRepository.getIsEnabledInUser(user);
    }

    @Override
    public Enum getPrivilege(User user) {
        return user.getPrivilege();
    }

    @Override
    public void setPrivilege(User user, Enum privilege) {
        user.setPrivilege(privilege);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
