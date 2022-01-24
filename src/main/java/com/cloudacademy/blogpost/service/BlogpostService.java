/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.BlogpostRepository;
import java.util.Optional;
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

    public void deletePost(long l) {
        repository.deleteById(l);
    }
    
    public Post updatePost(Long postId, String title, String content, String author, String image) throws PostNotFoundException {
        Optional<Post> opt = repository.findById(postId);
        if (!opt.isPresent()) throw new PostNotFoundException();
        Post post = opt.get();
        if (title != null) post.setTitle(title);
        if (content != null) post.setContent(content);
        if (author != null) post.setAuthor(author);
        if (image != null) post.setImage(image);
        return repository.save(post);
    }
}
