/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.service.PostService;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Andrea
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@OverrideAutoConfiguration(enabled=true)
public class BlogpostRestControllerRestTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    PostService service;
    
    
    @Test
    public void getPostsTest() throws Exception {
        List<Post> posts = Arrays.asList(new Post("a", "b", "c", "d"), new Post("1", "2", "3", "4"));
        
        System.out.println(service);
        System.out.println(mockMvc);
        when(service.getPosts()).thenReturn(posts);
        mockMvc.perform(get("/blogpost/posts")).andDo(print()).andExpect(status().isOk());
        
    }
}
