package com.example.demo.question;

import com.example.demo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    private void validateAnswers(Question question) {
        if(question.getCorrectAnswer()==null)
            throw new BadRequestException("Correct answer is not set");
        if(question.getAnswers().size() <= 1)
            throw new BadRequestException("Question must have at least 2 answers");
        if(!question.getAnswers().contains(question.getCorrectAnswer()))
            throw new BadRequestException("Correct answer is not in the list of answers");
    }

    public Question getQuestion(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public Question addNewQuestion(Question question) {
        validateAnswers(question);
        return questionRepository.save(question);
    }

    public Integer addNewQuestions(List<Question> questions) {
       questionRepository.saveAll(questions);
       return questions.size();
    }

    public Question updateQuestion(Question question) {
        if(getQuestion(question.getId()) == null)
            throw new BadRequestException("Question with id " + question.getId() + " does not exist");
        validateAnswers(question);
        return questionRepository.save(question);
    }
}
