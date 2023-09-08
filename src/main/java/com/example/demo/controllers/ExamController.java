package com.example.demo.controllers;

import com.example.demo.models.Exam;
import com.example.demo.models.Question;
import com.example.demo.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @GetMapping("/{examId}/questions")
    public List<Question> getQuestionsForAnExam(@PathVariable("examId") String examId) {
        return examService.getQuestionsByExamId(examId);
    }


    @PostMapping("/{examId}/upload-questions")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void uploadQuestionsToExam(@PathVariable("examId") String examId, @RequestParam("file") MultipartFile file)  {
        try {
            examService.addQuestionsToExam(examId, file);
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}