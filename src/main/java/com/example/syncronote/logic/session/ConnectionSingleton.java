package com.example.syncronote.logic.session;

import com.example.syncronote.logic.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionSingleton {
    private static ConnectionSingleton instance = null;

    private User sessionUser;
    private String userConfig;
    private String passConfig;
    private String dbUrlConfig;

    protected ConnectionSingleton() throws IOException{
        Properties prop = new Properties();

        InputStream is = new FileInputStream("src/main/java/com/example/syncronote/logic/dao/config.properties");

        prop.load(is);

        userConfig = prop.getProperty("USER");
        passConfig = prop.getProperty("PASS");
        dbUrlConfig = prop.getProperty("DB_URL");
    }

    public static synchronized ConnectionSingleton getInstance() throws IOException{
        if(ConnectionSingleton.instance == null){
            ConnectionSingleton.instance = new ConnectionSingleton();
        }

        return instance;
    }

    public String getUserConfig() {
        return userConfig;
    }

    public String getPassConfig() {
        return passConfig;
    }

    public String getDbUrlConfig() {
        return dbUrlConfig;
    }
}
