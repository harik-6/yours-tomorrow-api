package com.example.demo.services;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.Option;
import com.example.demo.models.Question;
import com.example.demo.models.Subject;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    private void validateQuestion(Question question) {
        Option answer = question.getAnswer();
        for(Option options:question.getOptions()) {
            if(options.getValue().equals(answer.getValue())) {
                return;
            }
        }
        throw new BadRequestException("answer is not present options");
    }

    public Question getQuestion(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Transactional
    public Question addNewQuestion(Question question) {
        validateQuestion(question);
        Option requestAnswer = question.getAnswer();
        Subject requestSubject = question.getSubject();
        question.setAnswer(null);
        question.setSubject(null);
        question.setTopic(null);
        // save the question without answer,subject and topic
        Question savedQuestion = questionRepository.save(question);
        // fetch the option and save again
        for(Option option: savedQuestion.getOptions()) {
            if(option.getValue().equals(requestAnswer.getValue())) {
                savedQuestion.setAnswer(option);
                break;
            }
        }
        // fetch is the same subject already present in subject table
        Subject existingSubject =  subjectRepository.findByName(requestSubject.getName());
        savedQuestion.setSubject(Objects.requireNonNullElse(existingSubject, requestSubject));
        return questionRepository.save(savedQuestion);
    }

    @Transactional
    public List<Question> addNewQuestions(List<Question> questions) {
        List<Question> savedQuestions = new ArrayList<>(questions.size());
       for(Question q : questions) {
           savedQuestions.add(addNewQuestion(q));
       }
       return savedQuestions;
    }

//    public Question updateQuestion(Question question) {
//        if(getQuestion(question.getId()) == null)
//            throw new BadRequestException("Question with id " + question.getId() + " does not exist");
//        validateAnswers(question);
//        return questionRepository.save(question);
//    }
}
