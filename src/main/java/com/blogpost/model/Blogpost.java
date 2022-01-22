/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogpost.model;

import java.util.List;

/**
 *
 * @author Andrea
 */
public class Blogpost {
    
    private long id;
    private String title;
    private String content;
    private String category;
    private String author;
    private String image;
    private List<String> tags;
    
    public Blogpost() {}

    public Blogpost(String title, String content, String category, String author, String image, List<String> tags) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.image = image;
        this.tags = tags;
    }
    
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    
    
}
