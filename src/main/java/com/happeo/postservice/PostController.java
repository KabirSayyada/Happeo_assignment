package com.happeo.postservice;

import java.sql.Connection;
import java.util.Scanner;

import com.happeo.dao.PostDao;
import com.happeo.utils.Database;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * The PostController class handles the interaction between the user interface and the PostService.
 */
/**
 * The PostController class is responsible for handling post-related operations.
 */
public class PostController {
    private PostService postService;
    private Database database;
    private PostDao postDao;
    private Validator validator;
    private Scanner scanner;

    /**
     * Constructs a PostController object with the specified dependencies.
     *
     * @param postService The PostService instance to be used.
     * @param postDao     The PostDao instance to be used.
     * @param database    The Database instance to be used.
     */
    public PostController(PostService postService, PostDao postDao, Database database) {
        Connection connection = database.getConnection();
        this.database = database;
        this.postDao = postDao;
        this.postService = postService;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Constructs a PostController object with the specified PostService instance.
     *
     * @param postService The PostService instance to be used.
     */
    public PostController(PostService postService) {
        this.postService = postService;
        this.scanner = new Scanner(System.in);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new post.
     *
     * @param post The Post object to be created.
     */
    public void createPost(Post post) {
        // Validate the post object
        Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Post> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            System.out.println("Post validation error(s):\n" + sb.toString());
        } else {
            try {
                // Add the post using the postService
                postService.addPost(post);
            } catch (Exception e) {
                System.out.println("Failed to create post: " + e.getMessage());
            }
        }
    }

    /**
     * Lists all the posts.
     */
    public void listPosts() {
        try {
            for (Post post : postService.getPosts()) {
                System.out.println(post.getContent());
            }
        } catch (Exception e) {
            System.out.println("Failed to list posts: " + e.getMessage());
        }
    }
}