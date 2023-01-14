package com.example.syncronote.logic.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ParentDAO {

    //TODO: Take config strings from the property file

    /* BEGIN CODE SMELL */
    protected static String USER;
    protected static String PASS;
    protected static String DB_URL;
    /* END CODE SMELL */

    public ParentDAO(){
        Properties prop = new Properties();

        try {
            InputStream is = new FileInputStream("src/main/java/com/example/syncronote/logic/dao/config.properties");
            prop.load(is);
            USER = prop.getProperty("USER");
            PASS = prop.getProperty("PASS");
            DB_URL = prop.getProperty("DB_URL");

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
