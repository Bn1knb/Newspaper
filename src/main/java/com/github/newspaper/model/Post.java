package com.github.newspaper.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 10, message = "There must be some content!")
    private String content;

    @NotNull
    private boolean isApproved;

    @Size(min = 5, message = " Hedline must be at least 5 characters long")
    private String headLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private Date date;

    public Post() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @PrePersist
    void createAt() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approves) {
        isApproved = approves;
    }
}
