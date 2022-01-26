/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Andrea
 */
@Entity
public class Tag implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="tagName")
    private String tagName;
    
    @Column(name="uniquekey")
    private String uniqueKey;
    
    public Tag(){}
    
    public Tag(String tag, String uniqueKey) {
        this.tagName = tag;
        this.uniqueKey = uniqueKey;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTagName() {
        return tagName;
    }
    
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    public String getUniqueKey() {
        return uniqueKey;
    }
    
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
