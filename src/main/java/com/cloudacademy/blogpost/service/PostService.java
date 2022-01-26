/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Tag;
import com.cloudacademy.blogpost.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudacademy.blogpost.repository.PostRepository;
import com.cloudacademy.blogpost.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import javax.transaction.Transactional;

/**
 *
 * @author Andrea
 */

public interface PostService {

    Post getPost(Long postId) throws PostNotFoundException;

    public List<Post> getPosts();

    public Post createPost(String title, String content, String author, String image);

    @Transactional
    public void deletePost(Long l) throws PostNotFoundException;

    public Post updatePost(Long postId, String title, String content, String author, String image) throws PostNotFoundException;

    @Transactional
    public Post setCategory(Long postId, String categoryKey) throws PostNotFoundException, CategoryNotFoundException;

    @Transactional
    public Post findByTitleAndCategory(String title, String categoryUniqueKey) throws PostNotFoundException;

    @Transactional
    public List<Post> findByTitleOrCategoryNameAndTags(String title, String categoryName, Set<String> tagNames);

    @Transactional
    public Post addTag(Long postId, String tagName, String uniqueKey) throws PostNotFoundException;

    @Transactional
    public Post removeTag(Long postId, String tagUniqueKey) throws PostNotFoundException;

}
