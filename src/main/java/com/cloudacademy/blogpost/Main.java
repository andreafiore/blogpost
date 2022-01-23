/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.BlogpostRepository;
import com.cloudacademy.blogpost.repository.BlogpostRepositoryImpl;
import com.cloudacademy.blogpost.util.HibernateSessionManager;
import java.io.IOException;
import org.hibernate.Session;

/**
 *
 * @author Andrea
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Category c = new Category(1L, "Animals");
        
        BlogpostRepository repository = new BlogpostRepositoryImpl();
        Post post = new Post("title", "abcdef", "auth", "img.jpeg");
        repository.createPost(post);
        
        post.setAuthor("new auth");
        repository.updatePost(post);
        
        repository.deletePost(post);
        
        HibernateSessionManager.closeSession();
    }
}
