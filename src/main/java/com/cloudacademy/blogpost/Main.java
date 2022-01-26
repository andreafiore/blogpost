/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Andrea
 */
@EnableAutoConfiguration
@EnableJpaRepositories("com.cloudacademy.blogpost.*")
@SpringBootApplication
@EntityScan("com.cloudacademy.blogpost.*")
@ComponentScan(basePackages = { "com.cloudacademy.blogpost.*" })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
