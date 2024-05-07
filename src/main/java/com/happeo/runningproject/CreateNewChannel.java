package com.happeo.runningproject;

import java.util.Arrays;

import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;
import com.happeo.utils.DatabaseInitializer;

/**
 * This class is responsible for creating a new channel and adding members to it.
 */
public class CreateNewChannel {
    /**
     * The main method is the entry point of the program.
     * It creates a new channel, sets its properties, and adds it to the database.
     * It also adds members to the channel.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        //adding this in case you dont want to initialize through Main.java, it can be done from here
        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.setupDatabase();

        ChannelDao channelDao = new ChannelDao();
        Channel channel = new Channel();
        channel.setId(1400); // Add your Channel Id here, it must be unique from those already in the H2 database
        channel.setName("Hi Happeo, I am creating a new channel"); // Add channel name here, must be unique from those in the H2 database
        channel.setMemberIds(Arrays.asList(1, 3, 4, 7)); // Add memberIds here, Members must be registered users of the product or app (they must exist in Members Table in H2)
        try {
            channelDao.addChannel(channel);
            System.out.println("Channel created successfully, and members have been added");
        } catch (Exception e) {
            System.out.println("Failed to create channel");
            e.printStackTrace();
        }
    }
}