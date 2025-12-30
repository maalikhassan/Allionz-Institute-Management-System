package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySQL {

    private static Connection connection;
    private static Properties dbProperties;

    static {
        // Load database properties from file
        dbProperties = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            dbProperties.load(fis);
        } catch (IOException e) {
            System.err.println("Warning: Could not load database.properties file. Using default/environment variables.");
            // Fall back to environment variables or system properties
            loadFromEnvironment();
        }
    }

    private static void loadFromEnvironment() {
        // Fallback to environment variables if properties file not found
        dbProperties.setProperty("db.host", System.getenv().getOrDefault("DB_HOST", "localhost"));
        dbProperties.setProperty("db.port", System.getenv().getOrDefault("DB_PORT", "3306"));
        dbProperties.setProperty("db.name", System.getenv().getOrDefault("DB_NAME", "ims"));
        dbProperties.setProperty("db.username", System.getenv().getOrDefault("DB_USERNAME", "root"));
        dbProperties.setProperty("db.password", System.getenv().getOrDefault("DB_PASSWORD", ""));
        dbProperties.setProperty("db.ssl.mode", System.getenv().getOrDefault("DB_SSL_MODE", "DISABLED"));
    }

    public static void createConnection() throws Exception {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String host = dbProperties.getProperty("db.host");
                String port = dbProperties.getProperty("db.port");
                String database = dbProperties.getProperty("db.name");
                String username = dbProperties.getProperty("db.username");
                String password = dbProperties.getProperty("db.password");
                String sslMode = dbProperties.getProperty("db.ssl.mode", "REQUIRED");
                
                String url = String.format("jdbc:mysql://%s:%s/%s?ssl-mode=%s", 
                    host, port, database, sslMode);
                
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
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
