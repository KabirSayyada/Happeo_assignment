package com.happeo.emailservice;

import java.util.List;

import com.happeo.postservice.Post;


//the email controller class handles the sending of emails
// and the sending of emails to members
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    //this method sends an email to a single recipient
    public String sendEmail(String recipient, String subject, String content) {
        try {
            emailService.sendEmail(recipient, subject, content);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

    //this method sends an email to all the members of a channel
    public String sendEmailToMembers(Post post, List<Integer> memberIds) {
        try {
            emailService.sendEmailToMembers(post, memberIds);
            return "Emails sent successfully";
        } catch (Exception e) {
            return "Failed to send emails: " + e.getMessage();
        }
    }
}





//the code below is not of use based on current scope of the project but I
//added for reference purposes and when scalability is required
/*

public class EmailController {
    private EmailService emailService;
    private HttpServerFactory httpServerFactory;

    public EmailController(EmailService emailService, HttpServerFactory httpServerFactory) throws IOException{
        this.emailService = emailService;
        this.httpServerFactory = httpServerFactory;

        HttpServer server = httpServerFactory.createHttpServer(new InetSocketAddress(8000), 0);
        server.createContext("/sendEmail", new EmailHandler());
        server.start();
        
        //shows error when testing by mockito
        //HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        //server.createContext("/sendEmail", new EmailHandler());
        //server.start();
    }
    

    class EmailHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                    InputStream requestBody = exchange.getRequestBody();
                    //Scanner scanner = new Scanner(requestBody, "UTF-8");
                    //String body = scanner.useDelimiter("\\A").next();

                    String recipient = exchange.getRequestHeaders().getFirst("Recipient");
                    String subject = exchange.getRequestHeaders().getFirst("Subject");
                    //String content = body;

                    emailService.sendEmail(recipient, subject, content);
                    emailService.sendEmail(recipient, subject, content);

                    String response = "Email sent successfully";
                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());

                    scanner.close(); // Close the Scanner object to fix the resource leak issue
                    os.close();
                } else {
                    String response = "Invalid request method";
                    exchange.sendResponseHeaders(405, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                String response = "Error processing request: " + e.getMessage();
                exchange.sendResponseHeaders(500, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}*/