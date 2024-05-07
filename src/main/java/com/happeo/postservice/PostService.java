package com.happeo.postservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;
import com.happeo.dao.PostDao;
import com.happeo.emailservice.EmailController;
import com.happeo.emailservice.EmailService;

/**
 * The PostService class handles the logic for managing posts and notifying channel members.
 */
public class PostService {
    private List<Post> posts;
    private PostDao postDao;
    private ChannelDao channelDao;
    private EmailService emailService;
    private ExecutorService executorService;

    /**
     * Constructs a PostService object with the specified dependencies.
     *
     * @param postDao      The PostDao object for accessing and manipulating posts.
     * @param channelDao   The ChannelDao object for accessing and manipulating channels.
     * @param emailService The EmailService object for sending email notifications.
     */
    
    
    public PostService(PostDao postDao, ChannelDao channelDao, EmailService emailService) {
        this.posts = new ArrayList<>();
        this.postDao = postDao;
        this.channelDao = channelDao;
        this.emailService = emailService;

        // To ensure the Post Service can handle heavy loads without issues we use java's inbuilt ExecutorService
        // Create a thread pool with a fixed number of threads.
        // We can adjust the number of threads based on system's capabilities and application's requirements.
        executorService = Executors.newFixedThreadPool(500);
    }

    /**
     * Adds a post to the system and sends email notifications to channel members.
     *
     * @param post The post to be added.
     */
    public void addPost(Post post) {
        postDao.savePost(post);
        posts.add(post);
        executorService.submit((Runnable) () -> { // Provide the type of the lambda parameter
            Channel channel = channelDao.getChannel(post.getChannelId());
            for (Integer memberId : channel.getMemberIds()) {
                emailService.sendEmail(memberId.toString(), "New Post in Channel", post.getContent());
            }
        });
        
    }

    /**
     * Adds a post to the system, sends email notifications to all channel subscribers,
     *  and logs the result.
     *
     * @param post The post to be added.
     */
    
    public void addPostAndNotifyMembers(Post post) {
        PostDao postDao = new PostDao();
        EmailController emailController = new EmailController(emailService);
        try {
            postDao.addPost(post);
            List<Integer> memberIds = postDao.getMemberIdsFromChannelId(post.getChannelId());
            emailController.sendEmailToMembers(post, memberIds);
            System.out.println("Post added successfully and emails sent to subscribers");
        } catch (Exception e) {
            System.out.println("Failed to add post and send emails");
            e.printStackTrace();
        }
    }

    /**
     * Returns the list of posts in the system.
     *
     * @return The list of posts.
     */
    public List<Post> getPosts() {
        return this.posts;
    }
    // Don't forget to shut down the executor service when it's no longer needed.
    public void shutdown() {
        executorService.shutdown();
    }
}
