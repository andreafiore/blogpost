package com.cloudacademy.blogpost.dto;

import com.cloudacademy.blogpost.model.Post;
import com.cloudacademy.blogpost.model.Category;
import com.cloudacademy.blogpost.model.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class PostDTOTest {

    @Test
    public void postEntityToDTOTest() {
        Post post = new Post("My post", "Lorem ipsum...", "unknown", "image.jpg");
        post.setCategory(new Category("Books", "BOOKS"));
        post.setTags(Stream.of(new Tag("book", "BOOK"), new Tag("sport", "SPORT")).collect(toSet()));

        PostDTO dto = PostDTO.postEntityToDTO(post);

        assertPostDTO(dto, "My post", "unknown", "Lorem ipsum...", "image.jpg", "BOOKS", new String[]{"BOOK", "SPORT"});
    }


    @Test
    public void postEntitiesToDTOTest() {
        Post post1 = new Post("My post", "Lorem ipsum...", "unknown", "image.jpg");
        post1.setCategory(new Category("Books", "BOOKS"));

        Post post2 = new Post("second post", "test", "author", "image.png");
        post2.setCategory(new Category("Books", "BOOKS"));
        PostDTO[] dtos = PostDTO.postEntitiesToDTO(Arrays.<Post>asList(post1, post2));

        assertEquals(dtos.length, 2);
        assertPostDTO(dtos[0], "My post", "unknown","Lorem ipsum...", "image.jpg", "BOOKS", new String[]{});
        assertPostDTO(dtos[1], "second post", "author","test", "image.png", "BOOKS", new String[]{});
    }

    @Test
    public void dtoToJsonString() throws JsonProcessingException {
        PostDTO dto = new PostDTO();
        dto.setTitle("xxx");
        dto.setAuthor("auth");
        dto.setContent("content");
        dto.setImage("image");
        dto.setCategory("CATEGORY");
        dto.setTags(new String[]{"tag1", "tag2"});

        String jsonString = PostDTO.dtoToJsonString(dto);
        assertEquals("{\n" +
                "  \"title\" : \"xxx\",\n" +
                "  \"author\" : \"auth\",\n" +
                "  \"content\" : \"content\",\n" +
                "  \"image\" : \"image\",\n" +
                "  \"category\" : \"CATEGORY\",\n" +
                "  \"tags\" : [ \"tag1\", \"tag2\" ]\n" +
                "}", jsonString);
    }

    @Test
    public void dtosToJsonString() throws JsonProcessingException {
        PostDTO dto1 = new PostDTO();
        dto1.setTitle("xxx");
        dto1.setAuthor("auth");
        dto1.setContent("content");
        dto1.setImage("image");
        dto1.setCategory("CATEGORY");
        dto1.setTags(new String[]{"tag1", "tag2"});

        PostDTO dto2 = new PostDTO();
        dto2.setTitle("yyy");
        dto2.setAuthor("auth");
        dto2.setContent("abcdef");
        dto2.setImage("img");
        dto2.setCategory("TTTT");

        String jsonString = PostDTO.dtosToJsonString(dto1, dto2);
        assertEquals("[ {\n" +
                "  \"title\" : \"xxx\",\n" +
                "  \"author\" : \"auth\",\n" +
                "  \"content\" : \"content\",\n" +
                "  \"image\" : \"image\",\n" +
                "  \"category\" : \"CATEGORY\",\n" +
                "  \"tags\" : [ \"tag1\", \"tag2\" ]\n" +
                "}, {\n" +
                "  \"title\" : \"yyy\",\n" +
                "  \"author\" : \"auth\",\n" +
                "  \"content\" : \"abcdef\",\n" +
                "  \"image\" : \"img\",\n" +
                "  \"category\" : \"TTTT\"\n" +
                "} ]", jsonString);
    }

    private void assertPostDTO(PostDTO dto, String title, String author, String content, String image, String category, String[] tags) {
        assertEquals(dto.getTitle(), title);
        assertEquals(dto.getAuthor(), author);
        assertEquals(dto.getContent(), content);
        assertEquals(dto.getImage(), image);

        assertEquals(dto.getCategory(), category);
        assertEquals(dto.getTags().length, tags.length);
        assertTrue(Arrays.asList(tags).containsAll(Arrays.asList(tags)));
    }
}
