package com.example.syncronote.logic.session;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory() {}

    static {
        // Does not work if generating a jar file
        try (InputStream input = new FileInputStream("src/main/java/com/example/syncronote/logic/dao/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("DB_URL");
            String user = properties.getProperty("USER");
            String pass = properties.getProperty("PASS");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
