package com.happeo.utils;

import java.util.List;

import com.happeo.dao.PostDao;
import com.happeo.emailservice.EmailController;
import com.happeo.emailservice.EmailService;
import com.happeo.postservice.Post;

public class EmailSending {
    private EmailService emailService;

    public EmailSending(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmail(String recipient, String subject, String content) {
        // Log the email sending
        System.out.println("Sending email to " + recipient + " with subject " + subject + " and content " + content);

        // Call the actual email sending functionality
        emailService.sendEmail(recipient, subject, content);
    }

    public void addPostAndNotifyMembers(Post post, List<Integer> memberIds) {
        PostDao postDao = new PostDao();
        EmailController emailController = new EmailController(emailService); // Declare and initialize the emailController object
        try {
            postDao.addPost(post);
            emailController.sendEmailToMembers(post, memberIds);
        } catch (Exception e) {
            System.out.println("Failed to add post and send emails");
            e.printStackTrace();
        }
    }
}
