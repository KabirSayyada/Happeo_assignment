package com.happeo.emailservice;

import java.util.List;

import java.util.logging.Logger;

import com.happeo.postservice.Post;

/**
 * The EmailService class provides methods to send emails.
 */
public class EmailService {

//For auditing to troubleshoot customer reported issues we will be using a 
//Logger instance. the method logs an info message every time an email is sent.
//This is just a simple implementation, Please note that this will only log the 
//actions to the console. In a real world robust project. we will log the actions
//to a file.  or use a more robust auditing solution, or  library like Spring Data's 
//JPA Auditing or Hibernate Envers. These libraries can automatically track and log changes to data.
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());

    /**
     * Sends an email to the specified recipient.
     *
     * @param recipient the email recipient
     * @param subject   the email subject
     * @param content   the email content
     */
    public void sendEmail(String recipient, String subject, String content) {
        System.out.println("Sending email to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);

        // Log the email sending action
        logger.info("Sent email - Recipient: " + recipient + ", Subject: " + subject);
    }

    /**
     * Sends an email to all the members with the details of a post.
     *
     * @param post      the post object
     * @param memberIds the list of member IDs
     */
    public void sendEmailToMembers(Post post, List<Integer> memberIds) {
        for (Integer memberId : memberIds) {
            // Generate a dummy recipient email based on the member ID
            String recipient = "member" + memberId + "@example.com";
            String subject = "New post in your channel";
            String content = post.getContent();
            sendEmail(recipient, subject, content);
        }
        // Log the email sending action
        //logger.info("Sent email - Recipient: recipient + , Subject: subject");
    }
}