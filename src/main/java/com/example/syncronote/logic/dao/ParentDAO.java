package com.example.syncronote.logic.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ParentDAO {

    //TODO: Take config strings from the property file

    /* BEGIN CODE SMELL */
    protected static String userConfig;
    protected static String passConfig;
    protected static String dbUrlConfig;
    /* END CODE SMELL */

    public ParentDAO(){
        Properties prop = new Properties();

        try {
            InputStream is = new FileInputStream("src/main/java/com/example/syncronote/logic/dao/config.properties");
            prop.load(is);
            userConfig = prop.getProperty("USER");
            passConfig = prop.getProperty("PASS");
            dbUrlConfig = prop.getProperty("DB_URL");

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
