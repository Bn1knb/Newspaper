package com.github.newspaper.security.service.implementation;

import com.github.newspaper.dao.UserRepository;
import com.github.newspaper.entity.User;
import com.github.newspaper.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getPrivilege()));


        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword() , grantedAuthorities);
    }

//    @Override
//    public boolean isEnabled(User user) {
//        return userRepository.getIsEnabledInUser(user);
//    }
//TODO это добавить в имплементацию самого юзер сервиса
//    @Override
//    public String getPrivilege() {
//        return null;
//    }
//
//    @Override
//    public void setPrivilege(User user, Enum privilege) {
//        userRepository.setPrivilegeInUser(user, privilege);
//    }
}
