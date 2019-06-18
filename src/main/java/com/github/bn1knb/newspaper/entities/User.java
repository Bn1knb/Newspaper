package com.github.bn1knb.newspaper.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "name", nullable = false, unique = true)
    @NotNull
    @Size(min = 3)
    private String uName;

    //@Column(name = "password", nullable = false)
    @NotNull
    @Size(min = 6)
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    @Column(name = "approved", nullable = false)
    private boolean isApproved;

    @OneToMany(mappedBy = "user")
    private Set news;

    public User() {
    }

    public User(long id, String uName, String password, String passwordConfirm, Set role, boolean isApproved, Set news) {
        this.id = id;
        this.uName = uName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = role;
        this.isApproved = isApproved;
        this.news = news;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAproved() {
        return isApproved;
    }

    public void setAproved(boolean approved) {
        isApproved = approved;
    }

    public Set getNews() {
        return news;
    }

    public void setNews(Set news) {
        this.news = news;
    }
}
