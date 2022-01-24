/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
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

    public Post findByTitleAndCategoryName(String title, String name);

    //public Post setCategory(Post blogpost, Category category);

    /*public Blogpost setCategory(Blogpost blogpost, Category category);

    public Blogpost setTags(Blogpost blogpost, Set<Tag> tags);*/
}
