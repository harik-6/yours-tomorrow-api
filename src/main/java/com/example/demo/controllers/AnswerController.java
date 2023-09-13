package com.example.demo.controllers;

import com.example.demo.models.dtos.AnswerSubmissionDto;
import com.example.demo.services.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private UserAnswerService userAnswerService;

    @GetMapping("/exam/{examId}/user/{userId}")
    public void getAnswersForExamForAnUser(@PathVariable String examId, @PathVariable String userId) {
        //@Todo: return answer by the user
    }
    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnAnswer(@RequestBody AnswerSubmissionDto answerSubmissionDto,
                                  @RequestParam(value = "multiple",defaultValue = "false") boolean multiple) {
        //@Todo: check if multiple is true and handle multiple answers
         userAnswerService.addAnAnswer(answerSubmissionDto);
    }


}
