package com.happeo;

import com.happeo.utils.DatabaseInitializer;

/*import com.happeo.postservice.PostService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;
import com.happeo.dao.PostDao;
import com.happeo.emailservice.EmailService;
import com.happeo.utils.EmailSending;
import com.happeo.postservice.Post; // Import the Post class
import com.sun.net.httpserver.HttpExchange; // Import the HttpExchange class
import com.sun.net.httpserver.HttpServer; // Import the HttpServer class
import com.sun.net.httpserver.HttpHandler; // Import the HttpHandler class
*/

public class Main {
    public static void main(String[] args) throws Exception {
         DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.setupDatabase();
    }
}
   
/* 

// In a robust complex project i might use the static class below to
 //to handle the post request and response


 /
/*PostDao postDao = new PostDao();
        ChannelDao channelDao = new ChannelDao();
        EmailService emailService = new EmailService();
        EmailSending emailSending = new EmailSending(emailService);

        PostService postService = new PostService(postDao, channelDao, emailService);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api/posts", new PostHandler(postService));
        server.setExecutor(null); // creates a default executor
        server.start();

    static class PostHandler implements HttpHandler { 
        private PostService postService;

        public PostHandler(PostService postService) {
            this.postService = postService;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            if ("POST".equals(t.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String requestBody = br.lines().collect(Collectors.joining("\n"));

                String[] parts = requestBody.split(",");
                if (parts.length != 4) {
                    sendResponse(t, "Invalid request body", 400);
                    return;
                }

                int id = Integer.parseInt(parts[0]);
                int channelId = Integer.parseInt(parts[1]);
                String content = parts[2];
                int creatorId = Integer.parseInt(parts[3]);

                Post post = new Post(id, channelId, content, creatorId);
                postService.addPost(post);

                sendResponse(t, "Post created and email sent to subscribers", 200);
            } else {
                sendResponse(t, "Invalid request", 400);
            }
        }

        private void sendResponse(HttpExchange t, String response, int statusCode) throws IOException {
            t.sendResponseHeaders(statusCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}*/