package com.github.bn1knb.newspaper.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String uName;

    @Column(name = "password", nullable = false)
    @NotNull
    @Size(min=6)
    private String password;

    private String passwordConfirm;

    @Column(name = "role", nullable = false)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;

    @Column(name = "approved", nullable = false)
    private boolean isApproved;

    @Column(name = "news")
    private Set news;

    public User() {
    }

    public User(long id, String uName, String password, String passwordConfirm, List role, boolean isApproved, Set news) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    public boolean isAproved() {
        return isApproved;
    }

    public void setAproved(boolean approved) {
        isApproved = approved;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set getNews() {
        return news;
    }

    public void setNews(Set news) {
        this.news = news;
    }
}
