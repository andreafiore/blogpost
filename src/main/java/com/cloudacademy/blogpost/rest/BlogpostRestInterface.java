/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.service.CategoryNotFoundException;
import com.cloudacademy.blogpost.service.PostNotFoundException;
import com.cloudacademy.blogpost.dto.CategoryDTO;
import com.cloudacademy.blogpost.dto.PostDTO;
import com.cloudacademy.blogpost.dto.TagDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Andrea
 */
interface BlogpostRestInterface {
    
    ResponseEntity<PostDTO> getPost(Long postId) throws PostNotFoundException;
    
    ResponseEntity<PostDTO[]> getPosts() throws PostNotFoundException;


    
    ResponseEntity<PostDTO> addPost(PostDTO postDto);
    
    ResponseEntity<PostDTO> getPostByTitleAndCategory(String title, String category) throws PostNotFoundException;
    
    ResponseEntity<PostDTO> setPostCategory(Long postId, CategoryDTO category) throws PostNotFoundException, CategoryNotFoundException;
    
    ResponseEntity<PostDTO> updatePost(Long postId, PostDTO postDto) throws PostNotFoundException;
    
    ResponseEntity<Void> deletePost(String token, Long postId) throws PostNotFoundException, UserNotAuthorizedException;
    
    ResponseEntity<PostDTO> addTagToPost(Long postId, TagDTO tagDto) throws PostNotFoundException;
    
    ResponseEntity<PostDTO> removeTagFromPost(Long postId, String tagUniqueKey) throws PostNotFoundException;
}
