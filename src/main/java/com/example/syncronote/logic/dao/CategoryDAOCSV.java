package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class CategoryDAOCSV implements CategoryDAO{

    private File fd;
    private static final String CSV_FILE_NAME = "localDBFile.csv";
    private static final int INDEX_NAME = 1;
    private static final int INDEX_MACRO_AREA = 2;
    private static final int INDEX_GRADE = 3;

    public CategoryDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);

        if (!fd.exists()) {
            throw new IOException(CSV_FILE_NAME + " file does not exist");
        }
    }

    @Override
    public List<Category> selectCategories() throws IOException, CsvValidationException {
        return selectCategories(fd);
    }

    private List<Category> selectCategories(File fd) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] categoryRecord;
        List<Category> categoryList = new ArrayList<>();

        while ((categoryRecord = csvReader.readNext()) != null) {
            String name = categoryRecord[INDEX_NAME];
            String macroArea = categoryRecord[INDEX_MACRO_AREA];
            String gradeString = categoryRecord[INDEX_GRADE];
            GradeTypes gradeType = GradeTypes.fromString(gradeString);

            Category category = new Category(name, macroArea, gradeType);

            categoryList.add(category);
        }

        csvReader.close();

        return categoryList;
    }

    public void createCategory(Category category) throws Exception {
        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

        String[] categoryRecord = new String[3];

        categoryRecord[INDEX_NAME] = category.getName();
        categoryRecord[INDEX_MACRO_AREA] = category.getMacroArea();
        categoryRecord[INDEX_GRADE] = category.getGrade().getId();

        csvWriter.writeNext(categoryRecord);
        csvWriter.flush();
        csvWriter.close();
    }
}
