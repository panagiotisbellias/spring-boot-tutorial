package com.example.spring.boot.tutorial.repository;

import com.example.spring.boot.tutorial.model.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogTagRepository extends JpaRepository<BlogTag, Long> {
}