/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.service.CategoryNotFoundException;
import com.cloudacademy.blogpost.service.PostNotFoundException;
import com.cloudacademy.blogpost.ui.PostDTO;
import com.cloudacademy.blogpost.ui.TagDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Andrea
 */
interface BlogpostRestInterface {
    
    public ResponseEntity<PostDTO> getPost(Long postId) throws PostNotFoundException;
    
    public ResponseEntity<PostDTO[]> getPosts() throws PostNotFoundException;
    
    public ResponseEntity<PostDTO> addPost(PostDTO postDto);
    
    public ResponseEntity<PostDTO> getPostByTitleAndCategory(String title, String category) throws PostNotFoundException;
    
    public ResponseEntity<PostDTO> setPostCategory(Long postId, String category) throws PostNotFoundException, CategoryNotFoundException;
    
    public ResponseEntity<PostDTO> updatePost(Long postId, PostDTO postDto) throws PostNotFoundException;
    
    public ResponseEntity<Void> deletePost(Long postId) throws PostNotFoundException;
    
    public ResponseEntity<PostDTO> addTagToPost(Long postId, TagDTO tagDto) throws PostNotFoundException;
    
    public ResponseEntity<PostDTO> removeTagFromPost(Long postId, TagDTO tagDto) throws PostNotFoundException;
}
