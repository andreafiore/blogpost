/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.config;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Tag;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 *
 * @author Andrea
 */
public class HibernateConfiguration {
    
    private static final String DRIVER_PARAMETER = "database.driverClassName";
    private static final String URL_PARAMETER = "database.url";
    private static final String USER_PARAMETER = "database.username";
    private static final String PASSWORD_PARAMETER = "database.password";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String SHOW_SQL = "database.show-sql";
    private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    
    
    
    public static Configuration loadConfiguration() throws FileNotFoundException, IOException {
        Configuration configuration = new Configuration();
        
        try(InputStream inputStream =
                HibernateConfiguration.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            Properties configProps = new Properties();
            configProps.put(Environment.DRIVER, props.getProperty(DRIVER_PARAMETER));
            configProps.put(Environment.URL, props.getProperty(URL_PARAMETER));
            configProps.put(Environment.USER, props.getProperty(USER_PARAMETER));
            configProps.put(Environment.PASS, props.getProperty(PASSWORD_PARAMETER));
            configProps.put(Environment.DIALECT, props.getProperty(HIBERNATE_DIALECT));
            configProps.put(Environment.SHOW_SQL, props.getProperty(SHOW_SQL));
            configProps.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configProps.put(Environment.HBM2DDL_AUTO, props.getProperty(HBM2DDL_AUTO));
            configuration.setProperties(configProps);
            
            configuration.addAnnotatedClass(Post.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Tag.class);
        }
        return configuration;
    }
}
