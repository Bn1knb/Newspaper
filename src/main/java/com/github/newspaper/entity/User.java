package com.github.newspaper.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    private Date createdAt;

    private String privilege;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> news;

    public User() {
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String password, @NotNull String privilege) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public List<Post> getNews() {
        return news;
    }

    public void setNews(List<Post> news) {
        this.news = news;
    }

    @PrePersist
    void createAt() {
        this.createdAt = new Date();
    }
}
