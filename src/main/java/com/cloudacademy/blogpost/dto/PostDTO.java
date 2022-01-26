/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.dto;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Andrea
 */
@JsonInclude(Include.NON_NULL)
public class PostDTO {
    
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("content")
    private String content;
    @JsonProperty("image")
    private String image;
    @JsonProperty("category")
    private String category;
    @JsonProperty("tags")
    private String[] tags;
    
    
    private static final ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
    
    public static PostDTO postEntityToDTO(Post post) {
        Category category = post.getCategory();
        String categoryKey = category != null ? category.getUniqueKey() : null;
        Set<Tag> tags = post.getTags();
        String[] tagKeys = tags == null ? new String[0] : tags.stream().map(t -> t.getUniqueKey()).toArray(String[]::new);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setAuthor(post.getAuthor());
        postDTO.setContent(post.getContent());
        postDTO.setImage(post.getImage());
        postDTO.setCategory(categoryKey);
        postDTO.setTags(tagKeys);
        return postDTO;
    }
    
    public static PostDTO[] postEntitiesToDTO(List<Post> posts) {
        return posts.stream().map(PostDTO::postEntityToDTO).toArray(PostDTO[]::new);
    }

    public PostDTO() {}

    public PostDTO(Long id, String title, String author, String content, String image, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.image = image;
        this.category = category;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public static String dtosToJsonString(PostDTO ...dtos) throws JsonProcessingException {
        return mapper.writeValueAsString(dtos);
    }
    
    public static String dtoToJsonString(PostDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
}
