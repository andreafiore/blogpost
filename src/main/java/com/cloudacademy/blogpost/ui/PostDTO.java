/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudacademy.blogpost.ui;

import com.cloudacademy.blogpost.model.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author Andrea
 */
public class PostDTO {
    
    
    class PostSerializer extends StdSerializer<Post> {
        public PostSerializer() {
            this(null);
        }
        
        public PostSerializer(Class<Post> post) {
            super(post);
        }
        
        @Override
        public void serialize(Post post, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", post.getId());
            jsonGenerator.writeStringField("title", post.getTitle());
            jsonGenerator.writeStringField("author", post.getAuthor());
            jsonGenerator.writeStringField("content", post.getContent());
            jsonGenerator.writeStringField("image", post.getImage());
        }
    }
}
