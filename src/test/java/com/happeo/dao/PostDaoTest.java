package com.happeo.dao;

import com.happeo.postservice.Post;
import org.mockito.Mockito;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PostDaoTest {

    private PostDao postDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Before
    public void setup() throws SQLException {
        connection = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        postDao = new PostDao() {
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
        //postDao.setConnection(connection); // set connection, make sure you have internet connection
    }

    @Test
    public void testAddPost() throws SQLException {
        Post post = new Post(1, 1, "content", 1);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1); // Add this line
        postDao.addPost(post);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testSavePost() throws SQLException {
        // Arrange
        Post post = new Post(1, 1, "content", 1);
        
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        
        PostDao postDao = new PostDao();
        //postDao.setConnection(connection); // Set the mock connection
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        postDao.savePost(post);
        
        // Assert
      //  verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testSavePostException() throws SQLException {
        // Arrange
        Post post = new Post(1, 1, "content", 1);
        
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        
        PostDao postDao = new PostDao();
       // postDao.setConnection(connection); // Set the mock connection
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        
        // Act and Assert
    //    assertThrows(SQLException.class, () -> postDao.savePost(post));
    }

    @Test
public void testGetPosts() throws SQLException {
    // Arrange
    Post post = new Post(1, 1, "content", 1);
    List<Post> expectedPosts = Arrays.asList(post);
    
    ResultSet resultSet = mock(ResultSet.class);
    when(resultSet.next()).thenReturn(true, false); // Return true once, then false
    when(resultSet.getInt("id")).thenReturn(post.getId());
    when(resultSet.getInt("channelId")).thenReturn(post.getChannelId());
    when(resultSet.getString("content")).thenReturn(post.getContent());
    when(resultSet.getInt("creatorId")).thenReturn(post.getCreatorId());
    
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    
    PostDao postDao = new PostDao();
     // Assuming you have a method to set the connection
    
    
    // Act
    List<Post> actualPosts = postDao.getPosts();
    
    // Assert
    //assertEquals(expectedPosts.size(), actualPosts.size());
    //assertEquals(expectedPosts.get(1).getId(), actualPosts.get(1).getId());
    // Add more assertions as needed to verify the posts are correct
}
}