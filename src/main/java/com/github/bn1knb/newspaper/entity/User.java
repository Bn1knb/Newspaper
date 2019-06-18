package com.github.newspaper.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "COMMENT")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Email(message = "Email is incorrect")
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    private Date createdAt;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles;

    @OneToMany(targetEntity = News.class)
    private List<News> news;

    @PrePersist
    void createAt() {
        this.createdAt = new Date();
    }
}
