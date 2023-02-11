package com.example.syncronote.logic.patterns;

import com.example.syncronote.logic.dao.CategoryDAO;
import com.example.syncronote.logic.dao.CategoryDAOCSV;
import com.example.syncronote.logic.dao.CategoryDAOJDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CategoryDAOFactory {

    public CategoryDAO createCategoryDAO() throws IOException{
        InputStream input = new FileInputStream("src/main/java/com/example/syncronote/logic/dao/config.properties");
        Properties properties = new Properties();
        properties.load(input);

        String categoryDaoType = properties.getProperty("CATEGORY_DAO_TYPE");

        switch (categoryDaoType){
            case "jdbc": return new CategoryDAOJDBC();
            case "csv": return new CategoryDAOCSV();
            default: throw new IOException("Configuration file error");
        }
    }

    public CategoryDAO createCategoryDAOJDBC(){
        return new CategoryDAOJDBC();
    }

    public CategoryDAO createCategoryDAOCSV() throws IOException {
        return new CategoryDAOCSV();
    }
}
