package com.cloudacademy.blogpost.repository;

import com.cloudacademy.blogpost.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

    @Test
    public void findByUniqueKeyTest() throws Exception {
        Category category = new Category("Food", "FOOD");
        Category saved = repository.save(category);

        assertNotNull(saved);
        assertEquals(saved.getName(), "Food");
        assertEquals(saved.getUniqueKey(), "FOOD");
    }
}
