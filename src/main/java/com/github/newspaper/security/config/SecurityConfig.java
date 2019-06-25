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
        http
                .authorizeRequests()
                    .antMatchers("/admin/**")
                        .hasRole("ADMIN")
                    .antMatchers("/user/**")
                        .hasRole("USER")
                    .antMatchers("/moderator/**")
                        .hasRole("MODERATOR")
                    .antMatchers("/register")
                        .permitAll()
                    .antMatchers("/index")
                        .permitAll()
                    .antMatchers("/")
                        .permitAll()
                    .antMatchers("/feed")
                        .authenticated()
                    .antMatchers("/post/**")
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
