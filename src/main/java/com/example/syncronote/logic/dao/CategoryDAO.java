package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.model.Category;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    protected static final String NAME = "Name";
    protected static final String MACRO_AREA = "Macroarea";
    protected static final String GRADE = "Grade";

    public List<Category> selectCategories(Object... params) throws SQLException {
        List<Category> categories = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Category";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Category category = new Category(
                    rs.getString(1),
                    rs.getString(2),
                    GradeTypes.fromString(rs.getString(3)));
            categories.add(category);
        }

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return categories;
    }
}
