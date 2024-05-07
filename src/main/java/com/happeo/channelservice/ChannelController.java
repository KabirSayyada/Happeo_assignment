package com.happeo.channelservice;



import java.util.List;

public class ChannelController {
    private ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel createChannel(int id, String name, List<Integer> memberIds) {
        return channelService.createChannel(id, name, memberIds);
    }

    public Channel getChannel(int id) {
        return channelService.getChannel(id);
    }
}
