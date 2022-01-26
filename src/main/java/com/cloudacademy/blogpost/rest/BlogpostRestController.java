/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.service.CategoryNotFoundException;
import com.cloudacademy.blogpost.service.PostNotFoundException;
import com.cloudacademy.blogpost.service.PostService;
import com.cloudacademy.blogpost.ui.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrea
 */
@RestController
@RequestMapping("blogpost")
public class BlogpostRestController {
    
    @Autowired
    PostService service;
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) throws PostNotFoundException {
        Post post = service.getPost(postId);
        return new ResponseEntity<>(PostDTO.postEntityToDTO(post), HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostDTO[]> getPosts() throws PostNotFoundException {
        PostDTO[] posts = PostDTO.postEntitiesToDTO(service.getPosts());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostDTO> getPostByTitleAndCategory(@RequestParam String title, @RequestParam String category) throws PostNotFoundException {
        PostDTO post = PostDTO.postEntityToDTO(service.findByTitleAndCategory(title, category));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostDTO> setPostCategory(@RequestParam Long postId, @RequestBody String category) throws PostNotFoundException, CategoryNotFoundException {
        PostDTO post = PostDTO.postEntityToDTO(service.setCategory(postId, postId));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    
}
