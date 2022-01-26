/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.ui;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Andrea
 */
public class TagDTO {
    
    @JsonProperty("tag")
    private String tagName;
    @JsonProperty("key")
    private String uniqueKey;

    public TagDTO(String tagName, String uniqueKey) {
        this.tagName = tagName;
        this.uniqueKey = uniqueKey;
    }

    public TagDTO() {}

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
