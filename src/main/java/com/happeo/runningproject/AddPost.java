package com.happeo.runningproject;

import com.happeo.dao.ChannelDao;
import com.happeo.dao.PostDao;
import com.happeo.emailservice.EmailService;
import com.happeo.postservice.Post;
import com.happeo.postservice.PostService;
import com.happeo.utils.DatabaseInitializer;

/**
 * This class represents the main entry point for adding a post and notifying subscribers.
 * It allows a user to add a post and sends out emails to subscribers.
 * The purpose of this class is since we dont have a frontend
 * it provide a frontend where a user can interact and add posts,
 * and the necessary database operations are performed.
 */
public class AddPost {

    /**
     * The main method that is executed when running the AddPost class.
     * It initializes the database, creates necessary instances, adds a post, and notifies members.
     * If any exception occurs during the process, it prints an error message and the stack trace.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.setupDatabase();

        // Create instances of necessary classes
        PostDao postDao = new PostDao();
        ChannelDao channelDao = new ChannelDao();
        EmailService emailService = new EmailService();
        PostService postService = new PostService(postDao, channelDao, emailService);

        // Create a new post below , consider them as textfields in a mobile app or website
        //and input the values, just make sure id is unique, make sure channel id exists
        //and the creator id exists in the channel(refer to data.sql). A non member can't 
        //just make a post you can check data.sql to see pre-existing channels and
         //its subscribers if you haven't created any yourself 

        Post post = new Post(1334, 700, "Truly and honestly this is Final check of the post service by Kabir", 4);

        try {
            // Add the post to the database
            postDao.addPost(post);

            // Add the post and notify members
            postService.addPostAndNotifyMembers(post);

            System.out.println("Post added successfully and emails were sent out to subscribers");
        } catch (Exception e) {
            System.out.println("Failed to add post");
            e.printStackTrace();
        }
        //Now please check the terminal and see what happens. 
        //Then verify in H2 Console if you want, dont forget to disconnect H2 console
        //before running the program again.
    }
}
