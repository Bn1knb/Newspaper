package com.github.newspaper.repository;

import com.github.newspaper.entity.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
