/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.util;

import static com.cloudacademy.blogpost.config.HibernateConfiguration.loadConfiguration;
import java.io.IOException;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Andrea
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = loadConfiguration();
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (IOException | HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    
    public static void closeSession() {
        if (sessionFactory != null) {
            sessionFactory.close();
            StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
        }
    }
    
    public static <T extends Serializable> T save(T t) {
        Transaction transaction = null;
        try {
            System.out.println("session factory " + HibernateUtil.getSessionFactory());
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("session factory " + HibernateUtil.getSessionFactory());
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            T ret = (T) session.save(t);
            // commit transaction
            transaction.commit();
            HibernateUtil.closeSession();
            return ret;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
