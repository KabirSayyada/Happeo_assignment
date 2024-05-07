
//The code here isn't used in the project. It is added to show proof of extendabilty 
//in the project. To make my PostDao class more extendable, I introduced this interface
//that defines the methods that the post DAO could have. I have added different 
//implementations of this interface for different types of posts. 

import java.sql.SQLException;

import com.happeo.postservice.Post;

public interface PostDaoInterface {
    void addPost(Post post) throws SQLException;
    // other methods...
}

/* 
//then this class handles text based posts
public class TextPostDao implements PostDao {
    // connection details...

    @Override
    public void addPost(Post post) throws SQLException {
        // implementation for adding a text post...
    }

    // other methods...
}

//this class handles posts that are image based e.g jpg, png etc
public class ImagePostDao implements PostDao {
    // connection details...

    @Override
    public void addPost(Post post) throws SQLException {
        // implementation for adding an image post...
    }


    //this class handles posts that are video based e.g Mp4, Mkv etc
public class VideoPostDao implements PostDao {
    // connection details...

    @Override
    public void addPost(Post post) throws SQLException {
        // implementation for adding a video post...
    }

    // other methods...
}
*/