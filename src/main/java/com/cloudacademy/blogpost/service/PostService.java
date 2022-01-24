/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudacademy.blogpost.repository.PostRepository;
import javax.transaction.Transactional;

/**
 *
 * @author Andrea
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    public Post createPost(String title, String content, String author, String image) {
        Post post = new Post(title, content, author, image);
        return postRepository.save(post);
    }

    public void deletePost(long l) {
        postRepository.deleteById(l);
    }
    
    public Post updatePost(Long postId, String title, String content, String author, String image) throws PostNotFoundException {
        Optional<Post> opt = postRepository.findById(postId);
        if (!opt.isPresent()) throw new PostNotFoundException();
        Post post = opt.get();
        if (title != null) post.setTitle(title);
        if (content != null) post.setContent(content);
        if (author != null) post.setAuthor(author);
        if (image != null) post.setImage(image);
        return postRepository.save(post);
    }
    
    //public Post findByTitleAndCategory(Long postId, Long categoryId)
    @Transactional
    public Post setCategory(Long postId, Long categoryId) throws PostNotFoundException, CategoryNotFoundException {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if(!categoryOpt.isPresent()) throw new CategoryNotFoundException();
        Optional<Post> postOpt = postRepository.findById(postId);
        if(!postOpt.isPresent()) throw new PostNotFoundException();
        Post post = postOpt.get();
        Category category = categoryOpt.get();
        post.setCategory(category);
        return postRepository.save(post);
    }
}
