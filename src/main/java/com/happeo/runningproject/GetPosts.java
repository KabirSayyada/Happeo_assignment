package com.happeo.runningproject;

import java.sql.SQLException;
import java.util.List;

import com.happeo.dao.PostDao;
import com.happeo.postservice.Post;


//This class can also be run as a standalone Java application and will print out
// all the posts retrieved by the getPosts method in the log.

public class GetPosts {
    public static void main(String[] args) throws SQLException {
        PostDao postDao = new PostDao();

        System.out.println("Posts were fetched successfully");
        List<Post> posts = postDao.getPosts();
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
