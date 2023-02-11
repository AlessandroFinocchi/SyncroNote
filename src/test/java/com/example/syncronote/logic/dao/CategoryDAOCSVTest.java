package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOCSVTest {

    @Test
    void selectCategories() {
        try{
            CategoryDAO categoryDAO = new CategoryDAOCSV();
            List<Category> categoryList = categoryDAO.selectCategories();
            System.out.println("ciao");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void createCategory() {
        try{

            Category category = new Category(
                    "ISPW",
                    "Ingegneria",
                    GradeTypes.UNIVERSITY
            );

            CategoryDAOCSV dao = new CategoryDAOCSV();
            dao.createCategory(category);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}