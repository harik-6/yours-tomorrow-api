package com.example.demo.services;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.Exam;
import com.example.demo.models.ExamQuestion;
import com.example.demo.models.Question;
import com.example.demo.models.dtos.ExamDetailsDto;
import com.example.demo.repositories.ExamQuestionRepository;
import com.example.demo.utils.ValidatorUtil;
import com.example.demo.utils.XlsxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamQuestionService {
    @Autowired
    private ExamQuestionRepository examQuestionRepository;
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;

    public ExamQuestion getExamQuestion(String examId, String questionId) {
        return examQuestionRepository.findByExamIdAndQuestionId(examId, questionId).orElse(null);
    }

    public List<ExamQuestion> getExamQuestionsByExam(Exam exam) {
        return examQuestionRepository.findByExamId(exam);
    }

    public List<Question> getQuestionsByExamId(String examId) {
        Exam exam = examService.getExamById(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        List<ExamQuestion> examQuestions = getExamQuestionsByExam(exam);
        List<Question> questions = new ArrayList<>(examQuestions.size());
        for(ExamQuestion examQuestion: examQuestions) {
            questions.add(examQuestion.getQuestion());
        }
        return questions;
    }

    public ExamDetailsDto getExamDetailsByExamId(String examId) {
        Exam exam = examService.getExamById(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        return new ExamDetailsDto(exam,getQuestionsByExamId(examId));
    }

    @Transactional
    public List<ExamQuestion> addQuestionListToAnExam(Exam exam, List<Question> questions) {
        List<ExamQuestion> examQuestions = new ArrayList<>(questions.size());
        for(Question q: questions) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam);
            examQuestion.setQuestion(q);
            examQuestions.add(examQuestion);
        }
        return examQuestionRepository.saveAll(examQuestions);
    }

    public void removeQuestionFromExam(String examId,String questionId) {
        ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndQuestionId(examId, questionId).orElse(null);
        ValidatorUtil.validateDbRecord(examQuestion, "no exam question found for the exam id "+examId+" and question id "+questionId);
        examQuestionRepository.delete(examQuestion);
    }

    @Transactional
    public void addQuestionsToExamFromFile(String examId, MultipartFile file) throws IOException {
        Exam exam = examService.getExamById(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        System.out.printf("parsing file name %s for exam %s\n",file.getOriginalFilename(),exam.getName());
        // add questions to question table
        List<Question> questions =  XlsxUtil.parseFileToQuestion(file.getInputStream());
        if(questions.isEmpty()) {
            throw new BadRequestException("question parsing failed");
        }
        System.out.println("total questions parsed "+questions.size());
        List<Question> savedQuestions =  questionService.addNewQuestionList(questions);
        System.out.println("total questions saved "+savedQuestions.size());
        // update exam to question mapping table
        addQuestionListToAnExam(exam,savedQuestions);
    }

    @Transactional
    public void deleteAllQuestionsByExamId(String examId) {
        Exam exam = examService.getExamById(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        System.out.println("delete all questions from exam "+exam.getId());
        examQuestionRepository.removeAllQuestionIdForAnExamId(examId);
    }
}
