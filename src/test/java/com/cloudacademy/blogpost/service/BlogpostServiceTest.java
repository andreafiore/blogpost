/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.repository.BlogpostRepository;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author Andrea
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogpostServiceTest {

    @Mock
    BlogpostRepository repository;

    @InjectMocks
    BlogpostService service;

    @Test
    public void createPostTest() {
        when(repository.save(any(Post.class))).thenReturn(new Post());

        Post post = service.createPost("", "", "", "");

        assertNotNull(post);
    }

}
