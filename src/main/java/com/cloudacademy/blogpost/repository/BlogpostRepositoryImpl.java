/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.util.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andrea
 */
public class BlogpostRepositoryImpl implements BlogpostRepository{
    
    private static final Session session = HibernateSessionManager.getSessionFactory().openSession();

    @Override
    public Post createPost(Post post) {
        return save(post);
    }

    @Override
    public void deletePost(Post post) {
        delete(post);
    }

    @Override
    public Post updatePost(Post post) {
        return update(post);
    }
    
    public Post updatePost(Long id, String title, String content, String author, String image) {
        Post oldPost = findById(id);
        if (oldPost != null) {
            if (title != null) oldPost.setTitle(title);
            if (content != null) oldPost.setContent(content);
            if (author != null) oldPost.setAuthor(author);
            if (image != null) oldPost.setImage(image);
            return update(oldPost);
        }
        return null;
    }

    @Override
    public Post findByTitleAndCategoryId(String title, Long categoryId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post setCategory(Post blogpost, Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Post findById(long id) {
        Post post = (Post) session.get(Post.class, id);
        return post;
    }
    
    private Post save(Post post) {
        Transaction transaction = null;
        Post ret = null;
        try {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(post);
            ret = findById(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return ret;
    }
    
    private Post update(Post post) {
        Transaction transaction = null;
        Post ret = null;
        try {
            transaction = session.beginTransaction();
            session.update(post);
            ret = findById(post.getId());
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return ret;
    }
    
    private void delete(Post post) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(post);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
