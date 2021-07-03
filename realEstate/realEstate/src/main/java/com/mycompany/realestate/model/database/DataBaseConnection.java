
package com.mycompany.realestate.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for database connection
 * @author Yassine ibhir
 */
public class DataBaseConnection {
   
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/realestate";
    private static final String username = "root";
    private static final String password = "Flous101992";
    
    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
            } 
        catch (SQLException ex) {
            System.out.println("Failed to create the database connection."); 
            }
  
        return connection;
    }
}
