package com.happeo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseInitializer {

    public void setupDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement stmt = conn.createStatement()) {

             //Try to query each table
             try {
                stmt.executeQuery("SELECT 100 FROM Channels");
                stmt.executeQuery("SELECT 1 FROM Members");
                stmt.executeQuery("SELECT 1 FROM ChannelMembers");
                stmt.executeQuery("SELECT 10 FROM Posts");
                // If all queries succeeded, the tables exist and we don't need to do anything
                return;
             } catch (SQLException e) {
                // If any query failed, the corresponding table does not exist and we need to set up the database
            }
       
            // Read the SQL script
            InputStream is = getClass().getResourceAsStream("/data.sql");
            String sqlScript = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Split the script into individual statements
            String[] sqlStatements = sqlScript.split(";");

            // Execute each statement
            for (String sqlStatement : sqlStatements) {
                stmt.execute(sqlStatement);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}