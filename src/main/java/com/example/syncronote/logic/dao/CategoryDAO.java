package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> selectCategories() throws Exception;
}
