package com.happeo.runningproject;

import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;

/**
 * This class demonstrates how to fetch and display information about a channel.
 */
public class GetChannel {
    /**
     * The main method of the GetChannel class.
     * It fetches a channel with a specific ID and displays its details in the terminal/Log
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        ChannelDao channelDao = new ChannelDao();

        System.out.println("Channel was fetched successfully, information is below");

        try {
            Channel channel = channelDao.getChannel(600);
            if (channel != null) {
                System.out.println("Channel details:");
                System.out.println("ID: " + channel.getId());
                System.out.println("Name: " + channel.getName());
                System.out.println("Member IDs: " + channel.getMemberIds());
            } else {
                System.out.println("No channel found with ID: " + 800);
            }
        } catch (Exception e) {
            System.out.println("Failed to get channel");
            e.printStackTrace();
        }
    }
}
