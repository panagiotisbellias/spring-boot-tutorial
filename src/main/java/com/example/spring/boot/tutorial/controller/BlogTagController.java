package com.example.spring.boot.tutorial.controller;

import com.example.spring.boot.tutorial.model.BlogTag;
import com.example.spring.boot.tutorial.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/tags")
public class BlogTagController {

    @Autowired
    private BlogTagRepository blogTagRepository;

    @GetMapping
    public ResponseEntity<List<BlogTag>> getAllBlogTags() {
        List<BlogTag> returnBlogTags = blogTagRepository.findAll();
        return new ResponseEntity<>(returnBlogTags, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogTag> getBlogTagById(@PathVariable Long id) {
        if (blogTagRepository.existsById(id) && blogTagRepository.findById(id).isPresent()) {
            BlogTag blogTag = blogTagRepository.findById(id).get();
            return new ResponseEntity<>(blogTag, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<BlogTag> createBlogTag(@RequestBody BlogTag blogTag) {
        blogTagRepository.save(blogTag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogTag> updateBlogTagById(@PathVariable Long id, @RequestBody BlogTag blogTag) {
        Optional<BlogTag> blogTagToFind = blogTagRepository.findById(id);

        if (blogTagToFind.isPresent()) {
            BlogTag blogTagToUpdate = blogTagToFind.get();
            blogTagToUpdate.setName(blogTag.getName());

            blogTagRepository.save(blogTagToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            blogTagRepository.save(blogTag);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogTag> deleteBlogTagById(@PathVariable Long id) {
        if (blogTagRepository.existsById(id)) {
            blogTagRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}