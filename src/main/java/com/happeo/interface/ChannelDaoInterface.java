
//The code here isn't used in the project. It is added to show proof of extendabilty 
//in the project. To make my ChannelDao class more extendable, I introduced this interface
//that defines the methods that the Channel DAO could have.  to easily add support for new
 //types of channels in the future. If we want to add support for video channels,
 //for example, we could simply create a new class VideoChannelDao that implements 
 //the ChannelDao interface and create a channel solely for sharing videos.
 //I promise we won't call it reels channel!!

import java.sql.SQLException;

import com.happeo.channelservice.Channel;

public interface ChannelDaoInterface {
    void addChannel(Channel channel) throws SQLException;
    // other methods...
}

/**
 *  public class TextChannelDao implements ChannelDao {
    // connection details...

    @Override
    public void addChannel(Channel channel) throws SQLException {
        // implementation for adding a text channel...
    }

    // other methods...
}

public class ImageChannelDao implements ChannelDao {
    // connection details...

    @Override
    public void addChannel(Channel channel) throws SQLException {
        // implementation for adding an image channel...
    }

    public class VideoChannelDao implements ChannelDao {
    // connection details...

    @Override
    public void addChannel(Channel channel) throws SQLException {
      //   implementation for adding a video channel...
    }

    // other methods...
}
*/