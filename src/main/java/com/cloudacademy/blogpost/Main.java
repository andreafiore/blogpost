/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost;

import com.cloudacademy.blogpost.config.HibernateConfiguration;
import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.util.HibernateUtil;
import java.io.IOException;

/**
 *
 * @author Andrea
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Category c = new Category(1L, "Animals");
        
        HibernateUtil.save(c);
    }
}
