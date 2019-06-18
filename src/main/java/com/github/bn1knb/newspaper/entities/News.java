package com.github.bn1knb.newspaper.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String textContent;

    @Column(name = "headline")
    private String headline;

    @ManyToOne
    private User user;

    @Column(name = "comments")
    private Set comments;

    public News() {

    }

    public News(String textContent, String headline, User user, Set comments) {
        this.textContent = textContent;
        this.headline = headline;
        this.user = user;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set getComments() {
        return comments;
    }

    public void setComments(Set comments) {
        this.comments = comments;
    }
}
