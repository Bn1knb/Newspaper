package com.github.newspaper.service.implementation;

import com.github.newspaper.dao.UserRepository;
import com.github.newspaper.dto.UserRegistrationDto;
import com.github.newspaper.entity.User;
import com.github.newspaper.security.Role;
import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

//TODO add getters for data and other stuff

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isEnabled(User user) {
        return userRepository.IsEnabled(user);
    }

    //TODO change role in user to string not collection and covert to collection here note: used for cuxtom redirect

    @Override
    public void addRole(User user, Role role) {

        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void save(UserRegistrationDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.addRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid email");
        }
        return user;
    }

    @Override
    public void enable(User user) {
        user.setEnabled(true);
    }

    @Override
    public void disable(User user) {
        user.setEnabled(false);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(userName);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles){

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }


}
