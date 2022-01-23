/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogpost.repository;

import com.blogpost.model.Blogpost;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrea
 */
@Repository
public interface BlogpostRepository extends CrudRepository<Blogpost, Long>{
    
    @Override
    public Blogpost save(Blogpost blogpost);
    
    @Override
    public void deleteById(Long blogpostId);
    
    public Blogpost findByTitleAndCategory(String title, String category);
    
    public Blogpost findByTitleOrCategory(String title, String category);
    
    public List<Blogpost> findByTitleAndTags(String title, List<String> tags);
    
    public Blogpost assignBlogpostToCategory(Blogpost blogpost, String category);
}
