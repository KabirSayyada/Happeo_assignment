package com.happeo.postservice;

//what this class does is to create a post object and 
//set the values of the post object
public class Post {
    private int id;
    private int channelId;
    private String content;
    private int creatorId;

    public Post(int id, int channelId, String content, int creatorId) {
        this.id = id;
        this.channelId = channelId;
        this.content = content;
        this.creatorId = creatorId;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
