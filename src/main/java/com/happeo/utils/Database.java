package com.happeo.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Database connection
public class Database {
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Register H2 JDBC driver
            Class.forName("org.h2.Driver");

            // Create a connection to the H2 database
            String url = "jdbc:h2:~/test"; // Replace "test" with your database name
            String username = "sa"; // Replace "sa" with your username
            String password = ""; // Replace "" with your password
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
