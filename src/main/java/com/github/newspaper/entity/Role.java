package com.github.newspaper.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(targetEntity = User.class)
    private List<User> users;
}
