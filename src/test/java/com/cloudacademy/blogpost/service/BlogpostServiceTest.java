/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.BlogpostRepository;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author Andrea
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogpostServiceTest {

    @Mock
    BlogpostRepository postRepository;

    @InjectMocks
    BlogpostService service;

    @Test
    public void createPostTest() {
        when(postRepository.save(any(Post.class))).thenReturn(new Post("title", "content", "auth", "img.jpg"));

        Post post = service.createPost("", "", "", "");

        assertNotNull(post);
        assertEquals(post.getTitle(), "title");
        assertEquals(post.getContent(), "content");
        assertEquals(post.getAuthor(), "auth");
        assertEquals(post.getImage(), "img.jpg");
    }
    
    @Test
    public void deletePostTest() {
        doNothing().when(postRepository).deleteById(any(Long.class));
        
        service.deletePost(1L);
        
        verify(postRepository).deleteById(any(Long.class));
    }
    
    @Test
    public void updatePost() throws PostNotFoundException {
        Post post = new Post("Post test", "content", "author", "xxx.jpg");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(new Post("new Post test", "new content", "author1", "yyy.jpg"));
        
        Post updatedPost = service.updatePost(1L, "new Post test", "new content", "author1", "yyy.jpg");
        assertEquals(updatedPost.getTitle(), "new Post test");
        assertEquals(updatedPost.getContent(), "new content");
        assertEquals(updatedPost.getAuthor(), "author1");
        assertEquals(updatedPost.getImage(), "yyy.jpg");
    }
    
    @Test
    public void partialUpdatePost() throws PostNotFoundException {
        Post post = new Post("Test", "Lorem Ipsum...", "Foo Bar", "abc.jpg");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(new Post("new Post test", "Lorem Ipsum...", "Foo Bar", "xyz.jpg"));
        
        Post updatedPost = service.updatePost(1L, "new Post test", null, null, null);
        assertEquals(updatedPost.getTitle(), "new Post test");
        assertEquals(updatedPost.getContent(), "Lorem Ipsum...");
        assertEquals(updatedPost.getAuthor(), "Foo Bar");
        assertEquals(updatedPost.getImage(), "xyz.jpg");
    }

}
