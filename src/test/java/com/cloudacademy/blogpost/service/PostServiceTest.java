/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Tag;
import com.cloudacademy.blogpost.repository.CategoryRepository;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import com.cloudacademy.blogpost.repository.PostRepository;
import com.cloudacademy.blogpost.repository.TagRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


/**
 *
 * @author Andrea
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    PostRepository postRepository;
    
    @Mock
    CategoryRepository categoryRepository;
    
    @Mock
    TagRepository tagRepository;

    @InjectMocks
    PostService service;

    @Test
    public void getPostTest() throws PostNotFoundException {
        Post post = new Post("title", "content", "author", "image_123.jpg");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        
        Post p = service.getPost(2L);
        assertNotNull(p);
        assertEquals(p.getTitle(), "title");
        assertEquals(p.getAuthor(), "author");
        assertEquals(p.getContent(), "content");
        assertEquals(p.getImage(), "image_123.jpg");
    }
    
    @Test
    public void getPostNotFound() throws PostNotFoundException {
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> {
            service.getPost(2L);
        });
        assertTrue(exception.toString().contains("PostNotFoundException"));
    }
    
    @Test
    public void getPosts() {
        List<Post> posts = Arrays.asList(new Post("", "", "", ""), new Post("a", "b", "c", "d"));
        when(postRepository.findAll()).thenReturn(posts);
        List<Post> p = service.getPosts();
        assertEquals(p.size(), 2);
    }
    
    @Test
    public void createPostTest() {
        when(postRepository.save(any(Post.class))).thenReturn(new Post("title", "content", "auth", "img.jpg"));

        Post post = service.createPost("", "", "", "");

        assertNotNull(post);
        assertEquals(post.getTitle(), "title");
        assertEquals(post.getContent(), "content");
        assertEquals(post.getAuthor(), "auth");
        assertEquals(post.getImage(), "img.jpg");
    }
    
    @Test
    public void deletePostTest() {
        doNothing().when(postRepository).deleteById(any(Long.class));
        
        service.deletePost(1L);
        
        verify(postRepository).deleteById(any(Long.class));
    }
    
    @Test
    public void updatePost() throws PostNotFoundException {
        Post post = new Post("Post test", "content", "author", "xxx.jpg");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(new Post("new Post test", "new content", "author1", "yyy.jpg"));
        
        Post updatedPost = service.updatePost(1L, "new Post test", "new content", "author1", "yyy.jpg");
        assertEquals(updatedPost.getTitle(), "new Post test");
        assertEquals(updatedPost.getContent(), "new content");
        assertEquals(updatedPost.getAuthor(), "author1");
        assertEquals(updatedPost.getImage(), "yyy.jpg");
    }
    
    @Test
    public void partialUpdatePost() throws PostNotFoundException {
        Post post = new Post("Test", "Lorem Ipsum...", "Foo Bar", "abc.jpg");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(new Post("new Post test", "Lorem Ipsum...", "Foo Bar", "xyz.jpg"));
        
        Post updatedPost = service.updatePost(1L, "new Post test", null, null, null);
        assertEquals(updatedPost.getTitle(), "new Post test");
        assertEquals(updatedPost.getContent(), "Lorem Ipsum...");
        assertEquals(updatedPost.getAuthor(), "Foo Bar");
        assertEquals(updatedPost.getImage(), "xyz.jpg");
    }
    
    @Test
    public void setCategoryTest() throws Exception {
        Post post = new Post("SetCategory", "Lorem Ipsum...", "Foo Bar", "image.png");
        Category category = new Category("Nature");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        post.setCategory(category);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        
        Post updatedPost = service.setCategory(1L, 1L);
        
        assertEquals(updatedPost.getTitle(), "SetCategory");
        assertEquals(updatedPost.getCategory().getName(), "Nature");
    }
    
    @Test
    public void setCategoryTestPostNotFound() throws Exception {
        Category category = new Category("Nature");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> {
            service.setCategory(1L, 1L);
        });
        System.out.println(exception.toString());
        assertTrue(exception.toString().contains("PostNotFoundException"));
    }
    
    @Test
    public void setCategoryTestCategoryNotFound() throws Exception {
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            service.setCategory(1L, 1L);
        });
        System.out.println(exception.toString());
        assertTrue(exception.toString().contains("CategoryNotFoundException"));
    }
    
    @Test
    public void findByTitleOrCategoryNameAndTags() {
        Set<Tag> tagSet1 = Arrays.asList(new Tag("nature"), new Tag("animals"), new Tag("photo")).stream().collect(toSet());
        Set<Tag> tagSet2 = Arrays.asList(new Tag("animals"), new Tag("food")).stream().collect(toSet());
        Category cat1 = new Category("Cat1");
        Category cat2 = new Category("Cat2");
        Post post1 = new Post("title1", "", "", "");
        Post post2 = new Post("title2", "", "", "");
        
        post1.setCategory(cat1);
        post1.setTags(tagSet1);
        
        post2.setCategory(cat2);
        post2.setTags(tagSet2);
        
        List<Post> posts = Arrays.asList(post1, post2);
        when(postRepository.findByTitleOrCategoryName(any(String.class), any(String.class))).thenReturn(posts);
        
        List<Post> retrievedPosts = service.findByTitleOrCategoryNameAndTags("title1", "Cat2", Arrays.asList("food").stream().collect(toSet()));
        
        assertEquals(retrievedPosts.size(), 1);
    }
    
    @Test
    public void addTagTest() throws PostNotFoundException {
        Post post = new Post("", "", "", "");
        Tag tag = new Tag("test_test");
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);
        Post newPost = new Post("", "", "", "");
        newPost.setTags(Arrays.asList(tag).stream().collect(toSet()));
        when(postRepository.save(any(Post.class))).thenReturn(newPost);
        
        Post postUpdated = service.addTag(1L, "test_test");
        assertNotNull(postUpdated);
        assertEquals(postUpdated.getTags().size(), 1);
        assertEquals(postUpdated.getTags().stream().collect(toList()).get(0).getTagName(), "test_test");
    }
    
    @Test
    public void addTagTestPostNotFound() throws PostNotFoundException {
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> {
            service.addTag(1L, "test");
        });
        assertTrue(exception.toString().contains("PostNotFoundException"));
    }
    
    @Test
    public void removeTag() throws PostNotFoundException {
        Post post = new Post("", "", "", "");
        Tag tag = new Tag("tag");
        tag.setId(1L);
        post.setTags(Arrays.asList(tag).stream().collect(toSet()));
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.of(post));
        
        Set<Tag> emptySet = (new ArrayList<Tag>()).stream().collect(toSet());
        post.setTags(emptySet);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        
        Post p = service.removeTag(1L, 1L);
        assertNotNull(p);
    }
    
    @Test
    public void removeTagPostNotFound() throws PostNotFoundException {
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> {
            service.addTag(1L, "test");
        });
        assertTrue(exception.toString().contains("PostNotFoundException"));
    }
    

}
