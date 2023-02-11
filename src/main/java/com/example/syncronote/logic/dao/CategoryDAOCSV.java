package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CategoryDAOCSV implements CategoryDAO{

    private File fd;
    private static final String CSV_FILE_NAME = "localDBFile.csv";

    public CategoryDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);

        if (!fd.exists()) {
            fd.createNewFile();
        }
    }

    private static class CategoryAttributesOrder {
        public static int getIndex_Name() {
            return 0;
        }

        public static int getIndex_MacroArea() {
            return 1;
        }

        public static int getIndex_Grade() {
            return 2;
        }
    }

    @Override
    public List<Category> selectCategories() throws Exception{
        return selectCategories(fd);
    }

    private List<Category> selectCategories(File fd) throws Exception{
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] record;
        List<Category> categoryList = new ArrayList<>();

        while ((record = csvReader.readNext()) != null) {
            String name = record[CategoryAttributesOrder.getIndex_Name()];
            String macroArea = record[CategoryAttributesOrder.getIndex_MacroArea()];
            String gradeString = record[CategoryAttributesOrder.getIndex_Grade()];
            GradeTypes gradeType = GradeTypes.fromString(gradeString);

            Category category = new Category(name, macroArea, gradeType);

            categoryList.add(category);
        }

        csvReader.close();

        if (categoryList.isEmpty()) {
            Exception e = new Exception("No Categories found");
            throw e;
        }

        return categoryList;
    }

    public void createCategory(Category category) throws Exception {
        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

        String[] record = new String[3];

        record[CategoryAttributesOrder.getIndex_Name()] = category.getName();
        record[CategoryAttributesOrder.getIndex_MacroArea()] = category.getMacroArea();
        record[CategoryAttributesOrder.getIndex_Grade()] = category.getGrade().getId();

        csvWriter.writeNext(record);
        csvWriter.flush();
        csvWriter.close();
    }
}
