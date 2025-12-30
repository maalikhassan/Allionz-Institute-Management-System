package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;

public class MySQL {

    private static Connection connection;

    public static void createConnection() throws Exception {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://avnadmin:*************@mysql-2058cc20-maalikhassan132-a8e9.b.aivencloud.com:22390/u272822984_ims?ssl-mode=REQUIRED", "****", "*************");
            }
        } catch (Exception e) {
            // new Connection_faild().setV
            throw e;
        }

    }

    public static ResultSet executeSearch(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static Integer executeIUD(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }

}
