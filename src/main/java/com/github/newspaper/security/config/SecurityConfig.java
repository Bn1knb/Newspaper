package com.github.newspaper.security.config;

import com.github.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //TODO add oauth to other pages
        http  //TODO add security to /users/delete and /users/approve to make users can see each other
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/users/**")
                .hasRole("ADMIN")
                .antMatchers("/user")
                .hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/moderator/**")
                .hasRole("MODERATOR")
                .antMatchers("/post/**")
                .hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("/register")
                .permitAll()
                .antMatchers("/index")
                .authenticated()
                .antMatchers("/")
                .authenticated()
                .antMatchers("/feed")
                .authenticated()
                .antMatchers("/posts/**")
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginPage(
                        "/login"
                ).defaultSuccessUrl("/feed", true);//.successHandler(//TODO custom sucsess handler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
