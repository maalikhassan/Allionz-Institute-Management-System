
package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;


public class MySQL {
    private static Connection connection;
    
    
     public static void createConnection() throws Exception {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://193.203.184.109:3306/u272822984_ims", "u272822984_allionz", "AllionzIMS123");
        }
    }

    
    public static ResultSet executeSearch(String query)throws Exception{
        createConnection();
        return connection.createStatement().executeQuery(query);
    }
    
    public static Integer executeIUD(String query) throws Exception{
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
    
}

