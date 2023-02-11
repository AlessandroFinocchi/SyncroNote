package com.example.syncronote.logic.patterns;

import com.example.syncronote.logic.dao.CategoryDAO;
import com.example.syncronote.logic.dao.CategoryDAOCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOFactoryTest {

    @Test
    void createCategoryDAO() {
        try{
            CategoryDAOFactory f = new CategoryDAOFactory();
            CategoryDAO c = f.createCategoryDAO();

        } catch (IOException e) {
            Assertions.fail();
        }
    }
}