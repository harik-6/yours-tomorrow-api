package com.example.demo.utils;

import com.example.demo.enums.DifficultyEnum;
import com.example.demo.models.Option;
import com.example.demo.models.Question;
import com.example.demo.models.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class FileHelper {
    private static String getCellValue(Cell column){
        switch (column.getCellType()) {
            case STRING:
                return column.getStringCellValue();
            case NUMERIC:
                return String.valueOf(column.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(column.getBooleanCellValue());
            default:
                return "";
        }
    }

    public static List<Question> parseFileToQuestion(InputStream fileStream) {
        List<Question> questions = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(fileStream);
            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row row = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                //iterate columns
                Iterator<Cell> columns = row.iterator();
                Question question = new Question();
                List<Option> options = new ArrayList<>(4);
                int columnNumber = 0;
                while (columns.hasNext()) {
                    Cell column = columns.next();
                    String columnValue = getCellValue(column);
                    switch (columnNumber) {
                        case 0:
                            question.setQuestion(columnValue);
                            break;
                        case 1, 2, 3, 4:
                            options.add(new Option(columnValue));
                            break;
                        case 5:
                            question.setDifficulty(DifficultyEnum.valueOf(columnValue.toUpperCase()));
                            break;
                        case 6:
                            question.setSubject(new Subject(columnValue));
                            break;
                        case 7:
                            question.setAnswer(new Option(columnValue));
                            break;
                    }
                    columnNumber+=1;
                }
                System.out.printf("question successfully parsed %s\n",question.getQuestion());
                question.setOptions(options);
                questions.add(question);
            }
            workbook.close();
        }catch (Exception ex) {
            System.out.printf("error in parsing questions %s\n",ex.getMessage());
            questions = Collections.emptyList();
        }
        return questions;
    }
}
