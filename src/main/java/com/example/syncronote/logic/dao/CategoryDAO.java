package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.model.Category;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    List<Category> selectCategories() throws SQLException, IOException, CsvValidationException;
}
