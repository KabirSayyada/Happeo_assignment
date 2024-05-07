package com.happeo.postservice;


import com.happeo.channelservice.Channel;
import com.happeo.dao.ChannelDao;
import com.happeo.dao.PostDao;
import com.happeo.emailservice.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PostServiceTest {
    private PostDao postDao;
    private ChannelDao channelDao;
    private EmailService emailService;
    private PostService postService;

    @Before
    public void setup() {
        postDao = Mockito.mock(PostDao.class);
        channelDao = Mockito.mock(ChannelDao.class);
        emailService = Mockito.mock(EmailService.class);
        postService = new PostService(postDao, channelDao, emailService);
    }

    @Test
    public void testAddPost() {
        Post post = new Post(1, 1, "Test content", 1);
        Channel channel = new Channel();
        channel.setId(1);
        channel.setMemberIds(new ArrayList<>());

        Mockito.when(channelDao.getChannel(any(Integer.class))).thenReturn(channel);

        postService.addPost(post);

        verify(postDao, times(1)).savePost(post);
        verify(emailService, times(channel.getMemberIds().size())).sendEmail(any(String.class), any(String.class), any(String.class));
    }
}