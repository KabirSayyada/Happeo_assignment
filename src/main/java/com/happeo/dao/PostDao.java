package com.happeo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happeo.postservice.Post;


/**
 * This class handles the data access operations for posts.
 */
public class PostDao {
    //Database URL for H2 database
    private static final String URL = "jdbc:h2:~/test";
    //Database Username
    private static final String USERNAME = "sa"; 
    //Database Password
    private static final String PASSWORD = ""; 
    //Connection object to interact with the database
    //private Connection connection;

     /**
     * This method is used to establish a connection with the database.
     * 
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
         // DriverManager is a service for managing a set of JDBC drivers.
        // getConnection establishes a connection to the database specified by the URL, username and password
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //public void setConnection(Connection connection) {
      //  this.connection = connection;
    //}

    
    //This creates and adds a new post to the database
    public void addPost(Post post) {
        String sql = "INSERT INTO posts (id, channelId, content, creatorId) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, post.getId());
            stmt.setInt(2, post.getChannelId());
            stmt.setString(3, post.getContent());
            stmt.setInt(4, post.getCreatorId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Proper error handling
            System.err.println("Error adding post: " + e.getMessage());
        }
    }


    //This method is used to save a post to the database
    public void savePost(Post post) {
        String sql = "UPDATE posts SET channelId = ?, content = ?, creatorId = ? WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, post.getChannelId());//here we are setting the channelId
            stmt.setString(2, post.getContent());//here we are setting the content
            stmt.setInt(3, post.getCreatorId());//here we are setting the creatorId
            stmt.setInt(4, post.getId());//here we are setting the id
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No rows were updated for post with ID: " + post.getId());
            }

        } catch (SQLException e) {
            // Proper error handling
            System.err.println("Error saving post: " + e.getMessage());
        }
    }

    /**
 * This method is used to fetch the member IDs associated with a given channel ID.
 * 
 * @param channelId The ID of the channel.
 * @return A list of member IDs associated with the channel.
 */


    public List<Integer> getMemberIdsFromChannelId(int channelId) throws SQLException{
        List<Integer> memberIds = new ArrayList<>();
        String sql = "SELECT memberId FROM ChannelMembers WHERE channelId = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, channelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                memberIds.add(rs.getInt("memberId"));
            }
            //properly handle the error
        } catch (SQLException e) {
            throw new SQLException("Error while fetching member IDs", e);
            //e.printStackTrace();
        }

        return memberIds;
    }

    /**
 * This method is used to fetch all posts from the database.
 * 
 * @return A list of all posts in the database.
 * @throws SQLException if a database access error occurs
 */
    public List<Post> getPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Post post = new Post(
                    rs.getInt("id"),
                    rs.getInt("channelId"),
                    rs.getString("content"),
                    rs.getInt("creatorId")
                );
                posts.add(post);
            }
    
        } catch (SQLException e) {
            throw new SQLException("Error while fetching posts", e);
        }
    
        return posts;
    }
}
