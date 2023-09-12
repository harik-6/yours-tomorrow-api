package com.example.demo.utils;

import com.example.demo.enums.DifficultyEnum;
import com.example.demo.enums.LanguageEnum;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.*;
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
public class XlsxUtil {
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

    private static List<Question> parseSheetToQuestion(Sheet sheet, LanguageEnum language) {
        System.out.println("parsing question for language="+language);
        List<Question> questions = new ArrayList<>();
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
            QuestionPhrase questionPhrase = new QuestionPhrase();
            questionPhrase.setLanguage(language);
            List<Option> options = new ArrayList<>(4);
            int columnNumber = 0;
            while (columns.hasNext()) {
                Cell column = columns.next();
                String columnValue = getCellValue(column);
                switch (columnNumber) {
                    case 0:
                        questionPhrase.setValue(columnValue);
                        break;
                    case 1, 2, 3, 4:
                        options.add(new Option(columnValue,language));
                        break;
                    case 5:
                        question.setDifficulty(DifficultyEnum.valueOf(columnValue.toUpperCase()));
                        break;
                    case 6:
                        question.setSubject(new Subject(columnValue));
                        break;
                    case 7:
                        question.setTopic(new Topic(columnValue));
                        break;
                    case 8:
                        question.setAnswers(List.of(new Option(columnValue,language)));
                        break;
                }
                columnNumber+=1;
            }
            question.setOptions(options);
            question.setQuestionPhrases(List.of(questionPhrase));
            questions.add(question);
        }
        return questions;
    }

    private static List flatten(List list1,List list2) {
        List flat = new ArrayList(list1.size()+list1.size());
        for(int i=0;i<list1.size();i++) flat.add(list1.get(i));
        for(int i=0;i<list2.size();i++) flat.add(list2.get(i));
        return flat;
    }

    public static List<Question> parseFileToQuestion(InputStream fileStream) {
        List<Question> combinedList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(fileStream);
            Sheet sheetEnglish = workbook.getSheetAt(0);
            Sheet sheetTamil = workbook.getSheetAt(1);
            List<Question> englishQuestions = parseSheetToQuestion(sheetEnglish,LanguageEnum.ENGLISH);
            System.out.println("total questions parsed for english "+englishQuestions.size());
            List<Question> tamilQuestions = parseSheetToQuestion(sheetTamil,LanguageEnum.TAMIL);
            System.out.println("total questions parsed for tamil "+tamilQuestions.size());
            if(englishQuestions.size()!=tamilQuestions.size()) {
                throw new BadRequestException("number of parsed english and tamil questions are not equal");
            }
            for(int i=0;i<englishQuestions.size();i++) {
                Question tamil = tamilQuestions.get(i);
                Question english = englishQuestions.get(i);
                Question combinedQuestion = new Question();
                //set question
                combinedQuestion.setDifficulty(english.getDifficulty());
                combinedQuestion.setTopic(english.getTopic());
                combinedQuestion.setSubject(english.getSubject());
                combinedQuestion.setQuestionPhrases(flatten(tamil.getQuestionPhrases(),english.getQuestionPhrases()));
                combinedQuestion.setAnswers(flatten(tamil.getAnswers(),english.getAnswers()));
                combinedQuestion.setOptions(flatten(tamil.getOptions(),english.getOptions()));
                //add to list
                combinedList.add(combinedQuestion);
            }
            workbook.close();
        }catch (Exception ex) {
            System.out.printf("error in parsing questions %s\n",ex.getMessage());
            ex.printStackTrace();
            combinedList = Collections.emptyList();
        }
        return combinedList;
    }
}
