package com.github.bn1knb.newspaper.repository.users;

import com.github.bn1knb.newspaper.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository {
    User findByUsername(String uName);
}
