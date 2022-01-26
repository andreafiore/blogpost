/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.ui;

import com.cloudacademy.blogpost.model.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Andrea
 */
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
    
    
    public static PostDTO postEntityToDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }
    
    public static PostDTO[] postEntitiesToDTO(List<Post> posts) {
        List<PostDTO> list = posts.stream().map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getAuthor()))
                .collect(toList());
        return list.toArray(new PostDTO[0]);
    }

    public PostDTO(Long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
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
}
