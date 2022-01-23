/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Tag;
import java.util.List;
import java.util.Set;
//import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Andrea
 */

public interface BlogpostRepository {
    
    public Post save(Post blogpost);
    
    public void deleteById(Long blogpostId);
    
    public Post update(Post blogpost);
    
    /*@Query("SELECT b FROM blogpost AS b WHERE b.title = :title AND b.category.categoryId = :categoryId")
    public Blogpost findByTitleAndCategoryId(String title, Long categoryId);
    
    public List<Blogpost> findByTags(Set<Tag> tags); */
    
    public Post setCategory(Post blogpost, Category category);
    
    //public Blogpost setTags(Blogpost blogpost, Set<Tag> tags);
}
