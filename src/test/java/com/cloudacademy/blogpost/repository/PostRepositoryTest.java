/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Andrea
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    
    @Autowired
    PostRepository postRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Test
    public void savePostTest() {
        Post post = new Post("title", "content", "author", "img.jpg");
        Post savedPost = postRepository.save(post);
        
        assertNotNull(savedPost);
        assertEquals(savedPost.getTitle(), "title");
        assertEquals(savedPost.getContent(), "content");
        assertEquals(savedPost.getAuthor(), "author");
        assertEquals(savedPost.getImage(), "img.jpg");
    }
    
    @Test
    public void deletePostByIdTest() {
        Post post = new Post("", "", "", "");
        Post savedPost = postRepository.save(post);
        
        postRepository.deleteById(savedPost.getId());
        
        Optional<Post> deleted = postRepository.findById(savedPost.getId());
        assertFalse(deleted.isPresent());
    }
    
    @Test
    @Transactional
    public void findByTitleAndCategoryNameTest() {
        Post post = new Post("a", "b", "c", "d");
        Category category = new Category("Food");
        categoryRepository.save(category);
        post.setCategory(category);
        
        postRepository.save(post);
        
        Post found = postRepository.findByTitleAndCategoryName("a", "Food");
        assertEquals(found.getTitle(), "a");
        assertEquals(found.getCategory().getName(), "Food");
    }
    
    @Test
    @Transactional
    public void findByTitleOrCategoryNameTest() {
        Post post = new Post("a", "b", "c", "d");
        Category category = new Category("Food");
        Post post2 = new Post("title", "", "", "");
        categoryRepository.save(category);
        post.setCategory(category);
        post2.setCategory(category);
        
        postRepository.save(post);
        postRepository.save(post2);
        
        List<Post> found = postRepository.findByTitleOrCategoryName("a", "Food");
        assertEquals(found.size(), 2);
    }
    
}
