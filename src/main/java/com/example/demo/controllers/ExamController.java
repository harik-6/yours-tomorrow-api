package com.example.demo.controllers;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Exam;
import com.example.demo.models.Question;
import com.example.demo.models.UserExam;
import com.example.demo.services.ExamService;
import com.example.demo.services.UserExamService;
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
    @Autowired
    private UserExamService userExamService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @GetMapping("/{examId}")
    public Exam getExam(@PathVariable("examId") String examId) {
        Exam exam = examService.getExam(examId);
        if(exam == null) throw new RecordNotFoundException("no exams found for the id "+examId);
        return exam;
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

    @PostMapping("/{examId}/join/{userId}")
    public UserExam join(@PathVariable("examId") String examId, @PathVariable("userId") String userId) {
        return userExamService.joinExam(examId, userId);
    }
}
