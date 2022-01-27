/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.entities.Post;
import com.cloudacademy.blogpost.entities.Category;
import com.cloudacademy.blogpost.entities.Tag;
import com.cloudacademy.blogpost.service.CategoryNotFoundException;
import com.cloudacademy.blogpost.service.PostNotFoundException;
import com.cloudacademy.blogpost.service.PostService;
import com.cloudacademy.blogpost.dto.CategoryDTO;
import com.cloudacademy.blogpost.dto.PostDTO;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Andrea
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@OverrideAutoConfiguration(enabled=true)
public class BlogpostRestControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    PostService service;
    
    
    @Test
    public void getPostsTest() throws Exception {
        List<Post> posts = Arrays.asList(new Post("a", "b", "c", "d"), new Post("1", "2", "3", "4"));
        PostDTO[] dtos = PostDTO.postEntitiesToDTO(posts);
        
        when(service.getPosts()).thenReturn(posts);
        mockMvc.perform(get("/blogpost/posts")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string(PostDTO.dtosToJsonString(dtos)));
             
        
    }
    
    @Test
    public void getPostTest() throws Exception {
        Post post = new Post("a", "b", "c", "d");
        PostDTO dto = PostDTO.postEntityToDTO(post);
        
        when(service.getPost(any(Long.class))).thenReturn(post);
        mockMvc.perform(get("/blogpost/posts/1")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string(PostDTO.dtoToJsonString(dto)));    
    }
    
    @Test
    public void getPostTestPostNotFound() throws Exception {
        when(service.getPost(any(Long.class))).thenThrow(PostNotFoundException.class);
        mockMvc.perform(get("/blogpost/posts/1")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPostTest() throws Exception {
        Post post = new Post("abc", "def", "xxx", "img.jpg");
        PostDTO dto = PostDTO.postEntityToDTO(post);
        when(service.createPost(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(post);

        mockMvc.perform(post("/blogpost/posts")
                .contentType("application/json")
                .content(PostDTO.dtoToJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(PostDTO.dtoToJsonString(dto)));
    }

    @Test
    public void getPostByTitleAndCategory() throws Exception {
        Post post = new Post("abc", "def", "xxx", "img.jpg");
        Category category = new Category("Travel", "TRAVEL");
        post.setCategory(category);

        PostDTO dto = PostDTO.postEntityToDTO(post);

        when(service.findByTitleAndCategory(any(String.class), any(String.class))).thenReturn(post);

        mockMvc.perform(get("/blogpost/posts/post?title=abc&category=TRAVEL"))
                .andExpect(status().isOk())
                .andExpect(content().string(PostDTO.dtoToJsonString(dto)));
    }

    @Test
    public void getPostByTitleAndCategoryPostNotFound() throws Exception {
        Post post = new Post("", "", "", "");
        Category category = new Category("Travel", "TRAVEL");
        post.setCategory(category);

        when(service.findByTitleAndCategory(any(String.class), any(String.class))).thenThrow(PostNotFoundException.class);

        mockMvc.perform(get("/blogpost/posts/post?title=abc&category=TRAVEL"))
                .andExpect(status().isNotFound());
                //.andExpect(content().string(PostDTO.dtoToJsonString(dto)));
    }

    @Test
    public void setCategoryToPost() throws Exception {
        Post post = new Post("123", "456", "789", "img.png");
        Category category = new Category("Travel", "TRAVEL");
        post.setCategory(category);
        PostDTO postDTO = PostDTO.postEntityToDTO(post);
        when(service.setCategory(any(Long.class), any(String.class))).thenReturn(post);

        mockMvc.perform(put("/blogpost/posts/1").contentType(MediaType.APPLICATION_JSON).content(CategoryDTO.toJson(CategoryDTO.categoryToDTO(category))))
                .andExpect(status().isOk())
                .andExpect(content().string(PostDTO.dtoToJsonString(postDTO)));
    }

    @Test
    public void setCategoryToPostPostNotFound() throws Exception {
        when(service.setCategory(any(Long.class), any(String.class))).thenThrow(PostNotFoundException.class);

        mockMvc.perform(put("/blogpost/posts/1").contentType(MediaType.APPLICATION_JSON).content("{\"key\":\"CATEGORY\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void setCategoryToPostCategoryNotFound() throws Exception {
        when(service.setCategory(any(Long.class), any(String.class))).thenThrow(CategoryNotFoundException.class);

        mockMvc.perform(put("/blogpost/posts/2").contentType(MediaType.APPLICATION_JSON).content("{\"key\":\"CATEGORY\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePost() throws Exception {
        Post post = new Post("My first post", "Lorem ipsum..", "Foo Bar", "img");

        when(service.updatePost(any(Long.class), nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class)))
                .thenReturn(post);

        mockMvc.perform(patch("/blogpost/posts/2").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"xx\", \"image\":\"img\",\"author\":\"abc\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePostTestPostNotFound() throws Exception {
        when(service.updatePost(any(Long.class), nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class)))
                .thenThrow(PostNotFoundException.class);

        mockMvc.perform(patch("/blogpost/posts/3").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"image\":\"img\",\"author\":\"abc\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePostTest() throws Exception {
        doNothing().when(service).deletePost(any(Long.class));

        mockMvc.perform(delete("/blogpost/posts/1").header("X-Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePostNotFoundTest() throws Exception {
        doThrow(new PostNotFoundException()).when(service).deletePost(any());
        mockMvc.perform(delete("/blogpost/posts/1").header("X-Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePostUserNorAuthorizedTest() throws Exception {
        mockMvc.perform(delete("/blogpost/posts/1").header("X-Authorization", "xxxxx").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void addTagToPostTest() throws Exception {
        Post post = new Post("", "", "", "");
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("travel", "TRAVEL")));
        post.setTags(tags);

        when(service.addTag(any(Long.class), any(String.class), any(String.class))).thenReturn(post);

        mockMvc.perform(put("/blogpost/posts/1/tag").contentType(MediaType.APPLICATION_JSON).content("{\"key\":\"FOOD\", \"tag\":\"food\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(PostDTO.dtoToJsonString(PostDTO.postEntityToDTO(post))));
    }

    @Test
    public void addTagToPostTestPostNotFound() throws Exception {
        when(service.addTag(any(Long.class), any(String.class), any(String.class))).thenThrow(PostNotFoundException.class);

        mockMvc.perform(put("/blogpost/posts/1/tag").contentType(MediaType.APPLICATION_JSON).content("{\"key\":\"FOOD\", \"tag\":\"food\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void removeTagFromPostTest() throws Exception {
        Post post = new Post("", "", "", "");
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("photography", "PHOTOGRAPHY")));
        post.setTags(tags);

        when(service.removeTag(any(Long.class), any(String.class))).thenReturn(post);

        mockMvc.perform(delete("/blogpost/posts/1/tag/PHOTOGRAPHY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void removeTagFromPostTestPostNotFound() throws Exception {
        when(service.removeTag(any(Long.class), any(String.class))).thenThrow(PostNotFoundException.class);

        mockMvc.perform(delete("/blogpost/posts/1/tag/PHOTOGRAPHY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
