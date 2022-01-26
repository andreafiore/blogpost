/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Andrea
 */
@Entity
public class Category implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="uniquekey")
    private String uniqueKey;
    
    public Category(String name, String uniqueKey) {
        this.name = name;
        this.uniqueKey = uniqueKey;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUniqueKey() {
        return uniqueKey;
    }
    
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
