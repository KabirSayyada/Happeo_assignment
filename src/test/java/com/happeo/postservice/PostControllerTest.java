package com.happeo.postservice;


import org.junit.Before;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    private PostService postService;
    private PostController postController;
    private Validator validator;

    @Before
    public void setup() {
        postService = Mockito.mock(PostService.class);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        postController = new PostController(postService);
    }

    @Test
    public void testCreatePost() {
        Post post = new Post(1, 1, "Test Content", 1);
        post.setContent("Test content");

        Set<ConstraintViolation<Post>> violations = validator.validate(post);
        assertEquals(0, violations.size());

        postController.createPost(post);
        verify(postService, times(1)).addPost(post);
    }

    @Test
    public void testListPosts() {
        Post post = new Post(10, 10, "Test for Post", 10);
        post.setContent("Test content");

        when(postService.getPosts()).thenReturn(Collections.singletonList(post));

        postController.listPosts();
        verify(postService, times(1)).getPosts();
    }
}