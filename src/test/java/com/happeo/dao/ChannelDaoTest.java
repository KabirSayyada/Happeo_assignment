package com.happeo.dao;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;



public class ChannelDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private ChannelDao channelDao;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        channelDao = new ChannelDao();
        channelDao.setConnection(connection); // Assuming you have a setter for Connection in your ChannelDao class
    }


    @Test
    public void testAddChannel() throws SQLException {
        Channel channel = new Channel();
        channel.setId(1);
        channel.setName("Test Channel");
        channel.setMemberIds(Arrays.asList(1, 2, 3));
    
        channelDao.addChannel(channel);
    }

    @Test
public void testGetChannel() throws SQLException {
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getInt("id")).thenReturn(1);
    when(resultSet.getString("name")).thenReturn("Test Channel");

    List<Integer> memberIds = Arrays.asList(1, 2, 3);
    when(resultSet.getObject("member_ids")).thenReturn(memberIds);

    Channel channel = channelDao.getChannel(1);

   
}
}