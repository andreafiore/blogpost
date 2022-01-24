/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrea
 */
@Service
public class BlogpostService {

    @Autowired
    BlogpostRepository repository;

    public Post createPost(String title, String content, String author, String image) {
        Post post = new Post(title, content, author, image);
        return repository.save(post);
    }
}
