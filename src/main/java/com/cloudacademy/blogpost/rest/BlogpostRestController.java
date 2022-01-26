/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrea
 */
@RestController
@RequestMapping("blogpost")
public class BlogpostRestController {
    
    @Autowired
    PostService service;
    
    @GetMapping("/test")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
    
    
}
