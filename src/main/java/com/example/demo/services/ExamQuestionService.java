package com.example.demo.services;

import com.example.demo.models.Exam;
import com.example.demo.models.ExamQuestion;
import com.example.demo.models.Question;
import com.example.demo.repositories.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamQuestionService {
    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    public ExamQuestion getExamQuestion(String examQuestionId) {
        return examQuestionRepository.findById(examQuestionId).orElse(null);
    }

    public ExamQuestion getExamQuestion(String examId, String questionId) {
        return examQuestionRepository.findByExamIdAndQuestionId(examId, questionId).orElse(null);
    }

    public List<ExamQuestion> getByExam(Exam exam) {
        return examQuestionRepository.findByExamId(exam);
    }

    public ExamQuestion addQuestionToAnExam(Exam exam, Question question) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        return examQuestionRepository.save(examQuestion);
    }

    @Transactional
    public List<ExamQuestion> addQuestionsToAnExam(Exam exam, List<Question> questions) {
        List<ExamQuestion> examQuestions = new ArrayList<>(questions.size());
        for(Question q: questions) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam);
            examQuestion.setQuestion(q);
            examQuestions.add(examQuestion);
        }
        return examQuestionRepository.saveAll(examQuestions);
    }
}
