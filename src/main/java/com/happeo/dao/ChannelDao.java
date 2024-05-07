package com.happeo.dao;

import java.util.ArrayList;
import java.util.List;

import com.happeo.channelservice.Channel;

import java.sql.*; // Add this import statement

/**
 * This class handles the data access operations for channels.
 */
public class ChannelDao{
    //Database URL for H2 database
    private static final String URL = "jdbc:h2:~/test";
    //Database Username
    private static final String USERNAME = "sa"; 
    //Database Password
    private static final String PASSWORD = ""; 
   
    //Connection object to interact with the database
    private Connection connection;
    
     public void setConnection(Connection connection) {
        this.connection = connection;
    }


     /**
     * This method is used to establish a connection with the database.
     * 
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */


 // This method is used to create a new channel in the database.
    public void addChannel(Channel channel) {
        String sql = "INSERT INTO Channels (id, name) VALUES (?, ?)";
        String sqlMember = "INSERT INTO ChannelMembers (channelId, memberId) VALUES (?, ?)";
    
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            conn.setAutoCommit(false);
    
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, channel.getId());
                stmt.setString(2, channel.getName());
                stmt.executeUpdate();
            }
    
            try (PreparedStatement stmt = conn.prepareStatement(sqlMember)) {
                for (Integer memberId : channel.getMemberIds()) {
                    stmt.setInt(1, channel.getId());
                    stmt.setInt(2, memberId);
                    stmt.executeUpdate();
                }
            }
    
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**The code below first fetches the channel details from the Channels table,
       and then fetches the member IDs from the ChannelMembers table.
       The member IDs are then set in the Channel object.
    * 
      @param id The ID of the channel to fetch.
 *    @return The fetched channel, or null if no channel was found with the given ID.
 *  */
      public Channel getChannel(int id) {
        Channel channel = null;

        // SQL query to fetch the channel
        String sql = "SELECT * FROM Channels WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Create a new Channel object and populate it with the data from the database
                channel = new Channel();
                channel.setId(rs.getInt("id"));
                channel.setName(rs.getString("name"));
            }
        }

        if (channel != null) {
            // Fetch the member IDs from the ChannelMembers table
            String sqlMember = "SELECT memberId FROM ChannelMembers WHERE channelId = ?";
            try (PreparedStatement stmt2 = conn.prepareStatement(sqlMember)) {
                stmt2.setInt(1, id);
                try (ResultSet rs = stmt2.executeQuery()) {
                    List<Integer> memberIds = new ArrayList<>();
                    while (rs.next()) {
                        memberIds.add(rs.getInt("memberId"));
                    }
                    // Set the member IDs of the channel
                    channel.setMemberIds(memberIds);
                }
            }
        }

    } catch (SQLException e) {
        //Print stacktrace for debugging purposes
        e.printStackTrace();
    }

    return channel;
}

// ...
}