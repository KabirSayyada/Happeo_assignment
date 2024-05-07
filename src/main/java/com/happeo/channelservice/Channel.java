package com.happeo.channelservice;

import java.util.List;


//what this class does is it creates a channel object and sets
// the id, name and memberIds of the channel object
public class Channel {
    private int id;
    private String name;
    private List<Integer> memberIds;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Integer> memberIds) {
        this.memberIds = memberIds;
    }
}

