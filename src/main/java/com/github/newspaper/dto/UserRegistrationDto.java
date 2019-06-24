package com.github.newspaper.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @NotNull
    @Size(min = 3, message = "Username must contain at leas 3 characters")
    @Size(max = 15, message = "Username is too long")
    private String username;

    @NotNull
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email")
    private String email;

    @NotNull
    @Size(min = 6, message = "Password must contain at least 6 characters")
    @Size(max = 30, message = "Password is too long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
