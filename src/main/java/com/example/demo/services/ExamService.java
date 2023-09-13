package com.example.demo.services;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.Exam;
import com.example.demo.models.ExamQuestion;
import com.example.demo.models.Question;
import com.example.demo.repositories.ExamRepository;
import com.example.demo.utils.ValidatorUtil;
import com.example.demo.utils.XlsxUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamQuestionService examQuestionService;

    public Exam getExam(String examId) {
        return examRepository.findById(examId).orElse(null);
    }

    public List<Question> getQuestionsByExamId(String examId) {
        Exam exam = getExam(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        List<ExamQuestion> examQuestions = examQuestionService.getByExam(exam);
        List<Question> questions = new ArrayList<>(examQuestions.size());
        for(ExamQuestion examQuestion: examQuestions) {
            questions.add(examQuestion.getQuestion());
        }
        return questions;
    }

    public Exam createExam(Exam exam) {
        try {
            return examRepository.save(exam);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    public void addQuestionsToExam(String examId, MultipartFile file) throws IOException {
        Exam exam = getExam(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        System.out.printf("parsing file name %s for exam %s\n",file.getOriginalFilename(),exam.getName());
        // add questions to question table
        List<Question> questions =  XlsxUtil.parseFileToQuestion(file.getInputStream());
        if(questions.isEmpty()) {
            throw new BadRequestException("question parsing failed");
        }
        System.out.println("total questions parsed "+questions.size());
        List<Question> savedQuestions =  questionService.addNewQuestions(questions);
        System.out.println("total questions saved "+savedQuestions.size());
        // update exam to question mapping table
        examQuestionService.addQuestionsToAnExam(exam,savedQuestions);
    }

}
