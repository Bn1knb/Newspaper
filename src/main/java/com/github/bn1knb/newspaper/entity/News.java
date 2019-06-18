package com.github.newspaper.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NEWS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String headLine;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments;

    private Date createdAt;

    @PrePersist
    void createAt() {
        this.createdAt = new Date();
    }
}
