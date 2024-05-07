package com.happeo.channelservice;


import com.happeo.dao.ChannelDao;

import java.util.List;

public class ChannelService {
    private ChannelDao channelDao;

    public ChannelService(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    public Channel createChannel(int id, String name, List<Integer> memberIds) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setName(name);
        channel.setMemberIds(memberIds);
        channelDao.addChannel(channel);
        return channel;
    }

    public Channel getChannel(int id) {
        return channelDao.getChannel(id);
    }
}
