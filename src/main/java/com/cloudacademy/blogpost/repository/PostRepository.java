/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Post;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrea
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
    
    @Override
    public Post save(Post blogpost);

    @Override
    public void deleteById(Long blogpostId);

    public Post findByTitleAndCategoryName(String title, String name);
    
    public List<Post> findByTitleOrCategoryName(String title, String categoryName);
}
