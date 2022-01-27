package com.cloudacademy.blogpost.service;

import com.cloudacademy.blogpost.entities.Category;
import com.cloudacademy.blogpost.entities.Post;
import com.cloudacademy.blogpost.entities.Tag;
import com.cloudacademy.blogpost.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudacademy.blogpost.repository.PostRepository;
import com.cloudacademy.blogpost.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import javax.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TagRepository tagsRepository;

    public Post getPost(Long postId) throws PostNotFoundException {
        Optional<Post> postOpt = postRepository.findById(postId);
        if(!postOpt.isPresent()) throw new PostNotFoundException();
        return postOpt.get();
    }

    public List<Post> getPosts() {
        List<Post> ret = new ArrayList();
        Iterable<Post> iterable = postRepository.findAll();
        for(Post post : iterable) ret.add(post);
        return ret;
    }

    public Post createPost(String title, String content, String author, String image) {
        Post post = new Post(title, content, author, image);
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long l) throws PostNotFoundException {
        Optional<Post> postOpt = postRepository.findById(l);
        if (!postOpt.isPresent()) throw new PostNotFoundException();
        postRepository.deleteById(l);
    }

    public Post updatePost(Long postId, String title, String content, String author, String image) throws PostNotFoundException {
        Optional<Post> opt = postRepository.findById(postId);
        if (!opt.isPresent()) throw new PostNotFoundException();
        Post post = opt.get();
        if (title != null) post.setTitle(title);
        if (content != null) post.setContent(content);
        if (author != null) post.setAuthor(author);
        if (image != null) post.setImage(image);
        return postRepository.save(post);
    }

    @Transactional
    public Post setCategory(Long postId, String categoryKey) throws PostNotFoundException, CategoryNotFoundException {
        Category category = categoryRepository.findByUniqueKey(categoryKey);
        if(category == null) throw new CategoryNotFoundException();
        Optional<Post> postOpt = postRepository.findById(postId);
        if(!postOpt.isPresent()) throw new PostNotFoundException();
        Post post = postOpt.get();
        post.setCategory(category);
        return postRepository.save(post);
    }

    @Transactional
    public Post findByTitleAndCategory(String title, String categoryUniqueKey) throws PostNotFoundException {
        Post post = postRepository.findByTitleAndCategoryUniqueKey(title, categoryUniqueKey);
        if (post == null) throw new PostNotFoundException();
        return post;
    }

    @Transactional
    public List<Post> findByTitleOrCategoryNameAndTags(String title, String categoryName, Set<String> tagNames) {
        List<Post> posts = postRepository.findByTitleOrCategoryName(title, categoryName);
        List<Post> ret = new ArrayList();
        posts.forEach((post) -> {
            Set<Tag> tags = post.getTags();
            Set<String> storedTagNames = tags.stream().map(t -> t.getTagName()).collect(toSet());
            if (storedTagNames.containsAll(tagNames)) {
                ret.add(post);
            }
        });
        return ret;
    }

    @Transactional
    public Post addTag(Long postId, String tagName, String uniqueKey) throws PostNotFoundException {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (!postOpt.isPresent()) throw new PostNotFoundException();
        Post post = postOpt.get();
        Set<Tag> tags = post.getTags();
        if (tags == null) {
            tags = new ArrayList<Tag>().stream().collect(toSet());
            post.setTags(tags);
        }
        Tag savedTag = tagsRepository.save(new Tag(tagName, uniqueKey));
        tags.add(savedTag);
        return postRepository.save(post);
    }

    @Transactional
    public Post removeTag(Long postId, String tagUniqueKey) throws PostNotFoundException {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (!postOpt.isPresent()) throw new PostNotFoundException();
        Post post = postOpt.get();
        Set<Tag> tags = post.getTags();
        tags.removeIf(t -> tagUniqueKey.equals(t.getUniqueKey()));
        return postRepository.save(post);
    }
}
