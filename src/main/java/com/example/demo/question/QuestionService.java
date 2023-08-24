package com.example.demo.question;

import com.example.demo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question getQuestion(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public Question addNewQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Integer addNewQuestions(List<Question> questions) {
       questionRepository.saveAll(questions);
       return questions.size();
    }

    public Question updateQuestion(Question question) {
        if(getQuestion(question.getId()) == null) {
            throw new BadRequestException("Question with id " + question.getId() + " does not exist");
        }
        return questionRepository.save(question);
    }
}
