package com.github.bn1knb.newspaper.service.implementation;

import com.github.bn1knb.newspaper.dao.UserRepository;
import com.github.bn1knb.newspaper.dto.UserRegistrationDto;
import com.github.bn1knb.newspaper.model.User;
import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO add getters for data and other stuff

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isEnabled(User user) {
        return userRepository.IsEnabled(user);
    }

    @Override
    public void setRole(User user, String role) {

        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void save(UserRegistrationDto userDto) {

        User user = new User();
        user.setEnabled(false);
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository
                .findUserByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("No such username"));
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email");
        }
        return user;
    }

    @Override
    public void enable(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void disable(User user) {
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllOrderByDate() {
        return userRepository.findAllbyOrderByCreatedAtDesc();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.findUserById(id));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {

        User user = userRepository
                .findUserByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("No such username"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }


}
