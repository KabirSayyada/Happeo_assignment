Project Title
This is a Java project that uses an H2 database to manage posts and channels. The channel allows users to share posts, articles, and links, similar to Facebook posts.  A Channel has members (subscribers).

A post creation API was created. Upon post creation, an email containing the post content is sent to all the subscribers of the channel the post belongs to.

This project can be run locally and interacted with through the terminal in Visual Studio Code. e.g after a post is created by running the code in AddPost.java the result of the operation and all database queries can be conveniently seen there. the post creation can be seen in the terminal, the email sending functionality to each channel member will also be seen. In case you run any of the runningproject files and see build failed do you want to continue?, Ignore that and select always continue(its not an issue). 
 

Getting Started
To get started with this project, its recommended to use Visual Studio Code IDE. You need to set up the H2 database(use in embedded mode, version used in project for both h2 console and pom.xml is  is 2.2.222). The database URL, username, and password are specified in the ChannelDao and PostDao classes in the com.happeo.dao package. The default settings are:

URL: jdbc:h2:~/test
Username: sa
Password: "" (empty)
You can change these settings if you want to match your local H2 database setup. But I recommend leaving it like that since it's in embedded mode.

The data.sql file in the resources directory contains the SQL commands to create the necessary tables and populate them with initial data. You should initialize the database and populate it by running Main.java to replicate the structure used during development.

Running the Project
The project can be run by executing the main method in the Main.java file in the com.happeo package. This will initialize the database and set up the necessary instances for the database.

In the project in(com.happeo package) you will see a runningproject folder which contains four java files that simulate a standalone application: If you wish to add a post to H2 database use AddPost.java(adhere to post creation instructions so you dont encounter problems), to create a channel run CreateChannel.java e.t.c

[AddPost.java] Allows you to add a post and notify subscribers.
[CreateNewChannel.java] Allows you to create a new channel and add members to it.
[GetPosts.java] Fetches all posts from the database.
[GetChannel.java] Fetches a specific channel by its ID.
Each of these files can be run individually to perform the corresponding operation. The results will be displayed in the terminal.

Extendability
This project can be extended in several ways. For example, by adding more features such as updating or deleting posts and channels, or implementing a user authentication system. You could also build a frontend for the application to provide a more user-friendly interface.

Functions
-The Post Service of this project can handle heavy amount of loads
-Auditing was added in the Email Service to make it possible to troubleshoot customer-reported issues
-The solution is easily deployable and runnable locally(even within the IDE and terminal)
-Errors and fallbacks were well and properly handled in all cases

Built With
-Java (purely java, no other technology was used e.g Springboot, Spring e.t.c were not used)
-H2 Database
-Author: Kabir Garba Ringim

License
This project is licensed under the MIT License - see the LICENSE.md file for details.



