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
import com.cloudacademy.blogpost.dto.CategoryDTO;
import com.cloudacademy.blogpost.dto.PostDTO;
import com.cloudacademy.blogpost.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Andrea
 */
@RestController
@RequestMapping("blogpost")
public class BlogpostRestController implements BlogpostRestInterface{

    private final String bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
    
    @Autowired
    PostService service;
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) throws PostNotFoundException {
        Post post = service.getPost(postId);
        return new ResponseEntity<>(PostDTO.postEntityToDTO(post), HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO[]> getPosts() throws PostNotFoundException {
        PostDTO[] posts = PostDTO.postEntitiesToDTO(service.getPosts());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts", method=RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDto) {
        PostDTO newPost = PostDTO.postEntityToDTO(service.createPost(postDto.getTitle(), postDto.getContent(), postDto.getTitle(), postDto.getImage()));
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/post", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> getPostByTitleAndCategory(@RequestParam String title, @RequestParam String category) throws PostNotFoundException {
        PostDTO post = PostDTO.postEntityToDTO(service.findByTitleAndCategory(title, category));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> setPostCategory(@PathVariable Long postId, @RequestBody CategoryDTO category) throws PostNotFoundException, CategoryNotFoundException {
        PostDTO post = PostDTO.postEntityToDTO(service.setCategory(postId, category.getUniqueKey()));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.PATCH, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDto) throws PostNotFoundException {
        PostDTO post = PostDTO.postEntityToDTO(service.updatePost(postId, postDto.getTitle(), postDto.getContent(), postDto.getAuthor(), postDto.getImage()));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}", method=RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<Void> deletePost(@RequestHeader("X-Authorization") String token, @PathVariable Long postId) throws PostNotFoundException, UserNotAuthorizedException {
        if (!bearerToken.equals(token)) throw new UserNotAuthorizedException();
        service.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}/tag", method=RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> addTagToPost(@PathVariable Long postId, @RequestBody TagDTO tagDto) throws PostNotFoundException {
        PostDTO dto = PostDTO.postEntityToDTO(service.addTag(postId, tagDto.getTagName(), tagDto.getUniqueKey()));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @RequestMapping(value="/posts/{postId}/tag/{tagUniqueKey}", method=RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public ResponseEntity<PostDTO> removeTagFromPost(@PathVariable Long postId, @PathVariable String tagUniqueKey) throws PostNotFoundException {
        PostDTO dto = PostDTO.postEntityToDTO(service.removeTag(postId, tagUniqueKey));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
}
