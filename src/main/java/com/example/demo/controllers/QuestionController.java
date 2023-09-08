package com.example.demo.controllers;

import com.example.demo.models.Question;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable("questionId") String questionId) {
        return questionService.getQuestion(questionId);
    }

    @PostMapping("")
    public Question postQuestino(@RequestBody Question question) {
        return questionService.addNewQuestion(question);
    }

    @PostMapping("/multiple")
    public Integer postMultipleQuestino(@RequestBody List<Question> questions) {
        return questionService.addNewQuestions(questions);
    }

//    @PutMapping("/{questionId}")
//    public Question putQuestino(@PathVariable("questionId") String questionId,@RequestBody Question question) {
//        question.setId(questionId);
//        return questionService.updateQuestion(question);
//    }


}
