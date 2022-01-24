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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrea
 */
@Repository
public interface BlogpostRepository extends CrudRepository<Post, Long>{
    
    @Override
    public Post save(Post blogpost);
    
    @Override
    public void deleteById(Long blogpostId);
    
    public Post update(Post blogpost);
    
    /*public Blogpost findByTitleAndCategory(String title, String category);
    
    public List<Blogpost> findByTags(Set<Tag> tags);
    
    public Blogpost setCategory(Blogpost blogpost, Category category);
    
    public Blogpost setTags(Blogpost blogpost, Set<Tag> tags);*/
}