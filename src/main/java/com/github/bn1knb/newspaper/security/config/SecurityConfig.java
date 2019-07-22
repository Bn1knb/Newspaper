package com.github.bn1knb.newspaper.security.config;

import com.github.bn1knb.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //TODO add oauth to other pages
        http
                .authorizeRequests()
                    .antMatchers("resources/**")
                        .permitAll()
                    .antMatchers("/admin/**")
                        .hasRole("ADMIN")
                    .antMatchers("/users/delete/**")
                        .hasRole("ADMIN")
                    .antMatchers("/users/enable/**")
                        .hasRole("ADMIN")
                    .antMatchers("/users/view/**")
                        .authenticated()
                    .antMatchers("/user")
                        .hasAnyRole("USER", "ADMIN", "MODERATOR")
                    .antMatchers("/moderator/**")
                        .hasRole("MODERATOR")
                    .antMatchers("/post/**")
                        .hasAnyRole("ADMIN", "MODERATOR")
                    .antMatchers("/register")
                        .anonymous()
                    .antMatchers("/index")
                        .permitAll()
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
                            ).defaultSuccessUrl("/feed", true)
                    .and()
                        .logout()
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login?logout")
                        .permitAll();
    }
}
