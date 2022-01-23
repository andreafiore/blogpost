/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Category;

/**
 *
 * @author Andrea
 */

public interface BlogpostRepository {
    
    public Post createPost(Post blogpost);
    
    public void deletePost(Post post);
    
    public Post updatePost(Post blogpost);
    
    public Post findByTitleAndCategoryId(String title, Long categoryId);
    
    public Post setCategory(Post blogpost, Category category);
    
    //public List<Blogpost> findByTags(Set<Tag> tags); */
    
    //public Blogpost setTags(Blogpost blogpost, Set<Tag> tags);
}
