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
        List<Option> answers = question.getAnswers();
        if(answers.isEmpty()) throw new BadRequestException("answers are not present");
        int correctAnswers = 0;
       for(Option answer:answers) {
           System.out.println("answer "+answer.getValue());
           for(Option options:question.getOptions()) {
               System.out.println("option "+options.getValue());
               if(options.getValue().equals(answer.getValue())) {
                   correctAnswers+=1;
               }
           }
       }
       if(correctAnswers!=2) throw new BadRequestException("answers are not present options");
    }

    public Question getQuestion(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Transactional
    public Question addNewQuestion(Question question) {
        validateQuestion(question);
        List<Option> requestAnswers = question.getAnswers();
        Subject requestSubject = question.getSubject();
        question.setAnswers(null);
        question.setSubject(null);
        question.setTopic(null);
        // save the question without answer,subject and topic
        Question savedQuestion = questionRepository.save(question);
        // fetch the option and save again
        savedQuestion.setAnswers(new ArrayList<>());
        for(Option answer:requestAnswers) {
            for(Option option: savedQuestion.getOptions()) {
                if(option.getValue().equals(answer.getValue())) {
                    savedQuestion.getAnswers().add(option);
                }
            }
        }
        // fetch is the same subject already present in subject table
        Subject existingSubject =  subjectRepository.findByName(requestSubject.getName());
        savedQuestion.setSubject(Objects.requireNonNullElse(existingSubject, requestSubject));
        return questionRepository.save(savedQuestion);
    }

    @Transactional
    public List<Question> addNewQuestionList(List<Question> questions) {
        List<Question> savedQuestions = new ArrayList<>(questions.size());
       for(Question q : questions) {
           savedQuestions.add(addNewQuestion(q));
       }
       return savedQuestions;
    }

    public Question updateQuestion(Question questionToUpdate) {
        Question existingQuestion = getQuestion(questionToUpdate.getId());
        // update question database
        // update question phrases db
        // update options database
        return questionRepository.save(existingQuestion);
    }
}
