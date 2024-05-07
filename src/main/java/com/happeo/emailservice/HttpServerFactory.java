package com.happeo.emailservice;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer; // Import the necessary package

//this interface was added because the HttpServer class is final and cannot be mocked
public interface HttpServerFactory {
    HttpServer createHttpServer(InetSocketAddress addr, int backlog) throws IOException;
}



//code below is added to showcase how I will use it in a real world complex scenario
/* 
public class EmailService {
    private HttpServer server;

    public EmailService(HttpServer server) {
        this.server = server;
    }

    public void start() throws IOException {
        server.start();
    }

    public void stop(int delay) {
        server.stop(delay);
    }
}
*/