package com.example.demo.controllers;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Exam;
import com.example.demo.models.Question;
import com.example.demo.models.dtos.ExamDetailsDto;
import com.example.demo.services.ExamQuestionService;
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
    @Autowired
    private ExamQuestionService examQuestionService;

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @GetMapping("/{examId}")
    public Exam getExam(@PathVariable("examId") String examId) {
        Exam exam = examService.getExamById(examId);
        if(exam == null) throw new RecordNotFoundException("no exams found for the id "+examId);
        return exam;
    }

    @GetMapping("/{examId}/questions")
    public List<Question> getExamQuestions(@PathVariable("examId") String examId) {
        return examQuestionService.getQuestionsByExamId(examId);
    }

    @GetMapping("/{examId}/details")
    public ExamDetailsDto getExamDetails(@PathVariable("examId") String examId) {
        return examQuestionService.getExamDetailsByExamId(examId);
    }


    @PostMapping("/{examId}/upload-questions")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void uploadQuestionsToExam(@PathVariable("examId") String examId, @RequestParam("file") MultipartFile file)  {
        try {
            examQuestionService.addQuestionsToExamFromFile(examId, file);
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{examId}/delete-questions")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAllQuestionsFromExam(@PathVariable("examId") String examId)  {
        examQuestionService.deleteAllQuestionsByExamId(examId);
    }

    @DeleteMapping("/{examId}/remove/question/{questionId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeQuestionFromExam(@PathVariable("examId") String examId, @PathVariable("questionId") String questionId) {
        examQuestionService.removeQuestionFromExam(examId, questionId);
    }
}
