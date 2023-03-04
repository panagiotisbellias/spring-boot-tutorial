package com.example.spring.boot.tutorial.repository;

import com.example.spring.boot.tutorial.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // FIXME ID is preserved after deleted entity
}