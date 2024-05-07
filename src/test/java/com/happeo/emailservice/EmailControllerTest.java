package com.happeo.emailservice;


import org.junit.*;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class EmailControllerTest {

    private EmailService emailService;
    private EmailController emailController;

    @Before
    public void setup() {
        emailService = Mockito.mock(EmailService.class);
        emailController = new EmailController(emailService);
    }

    @Test
    public void testSendEmailSuccess() {
        doNothing().when(emailService).sendEmail("test@example.com", "Test", "This is a test email");
        String response = emailController.sendEmail("test@example.com", "Test", "This is a test email");
        assertEquals("Email sent successfully", response);
    }

    @Test
    public void testSendEmailFailure() {
        doThrow(new RuntimeException("Failed to send email")).when(emailService).sendEmail("test@example.com", "Test", "This is a test email");
        String response = emailController.sendEmail("test@example.com", "Test", "This is a test email");
        assertEquals("Failed to send email: Failed to send email", response);
    }
}