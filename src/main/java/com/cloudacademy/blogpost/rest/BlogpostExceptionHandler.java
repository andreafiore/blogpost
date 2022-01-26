/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.rest;

import com.cloudacademy.blogpost.service.CategoryNotFoundException;
import com.cloudacademy.blogpost.service.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Andrea
 */
@ControllerAdvice
public class BlogpostExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<Object> handlePostNotFoundException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<Object> handleCategoryNotFoundException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public final ResponseEntity<Object> handleUserNotAuthorizedException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>("User Not Authorized", HttpStatus.UNAUTHORIZED);
    }

}
