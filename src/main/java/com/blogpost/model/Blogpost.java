/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogpost.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Andrea
 */
@Entity
public class Blogpost {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name="author")
    private String author;
    @Column(name="image")
    private String image;
    @ManyToMany(fetch=FetchType.LAZY)
    private Set<Tag> tags;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;
    
    public Blogpost() {}

    public Blogpost(String title, String content, String author, String image) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.image = image;
    }
    
    public Long getId() {
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
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
}
