package com.happeo.emailservice;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class EmailServiceTest {
    private EmailService emailService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        emailService = new EmailService();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSendEmail() {
        String recipient = "test@example.com";
        String subject = "Test Subject";
        String content = "Test Content";

        emailService.sendEmail(recipient, subject, content);

        assertTrue(outContent.toString().contains("Sending email to: " + recipient));
        assertTrue(outContent.toString().contains("Subject: " + subject));
        assertTrue(outContent.toString().contains("Content: " + content));

        System.setOut(originalOut);
    }
}
