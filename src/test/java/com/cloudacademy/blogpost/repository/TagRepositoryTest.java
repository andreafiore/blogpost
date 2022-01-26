/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Tag;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Andrea
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryTest {
    
    @Autowired
    TagRepository tagRepository;
    
    @Test
    public void saveAllTagsTest() {
        Tag tag1 = new Tag("#mountain", "MOUNTAIN");
        Tag tag2 = new Tag("#nature", "NATURE");
        
        List<Tag> tags = new ArrayList();
        tags.add(tag1);
        tags.add(tag2);
        
        tagRepository.saveAll(tags);
        
        Iterable<Tag> savedTags = tagRepository.findAll();
        int length = 0;
        for (Tag tag : savedTags)
            length++;
        assertEquals(length, 2);
    }
}
