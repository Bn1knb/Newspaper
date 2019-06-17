package com.github.bn1knb.newspaper.repository.news;

import com.github.bn1knb.newspaper.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository {
    News findByHeadline(String headline);
}
