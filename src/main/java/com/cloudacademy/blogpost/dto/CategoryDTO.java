package com.cloudacademy.blogpost.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.cloudacademy.blogpost.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CategoryDTO {

    @JsonProperty("key")
    private String uniqueKey;

    public static CategoryDTO categoryToDTO(Category category) {
        return new CategoryDTO(category.getUniqueKey());
    }

    public CategoryDTO() {}

    public CategoryDTO(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    private static final ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static String toJson(CategoryDTO dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
}
