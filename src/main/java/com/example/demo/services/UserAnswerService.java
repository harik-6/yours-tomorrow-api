package com.example.demo.services;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.*;
import com.example.demo.models.dtos.AnswerSubmissionDto;
import com.example.demo.repositories.OptionsRepository;
import com.example.demo.repositories.UserAnswerRepository;
import com.example.demo.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserExamService userExamService;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private OptionService optionService;

    @Transactional
    public UserAnswer addAnAnswer(AnswerSubmissionDto answerSubmissionDto) {
        String userId = answerSubmissionDto.getUserId();
        String examId = answerSubmissionDto.getExamId();
        String questionId = answerSubmissionDto.getQuestionId();
        String answerId = answerSubmissionDto.getAnswerId();
        // check if user is mapped for that exam
        UserExam userExam = userExamService.getUserExam(userId, examId);
        ValidatorUtil.validateDbRecord(userExam,String.format("user %s is not part of exam %s",userId,examId));
        // check if question part of exam
        ExamQuestion examQuestion = examQuestionService.getExamQuestion(examId, questionId);
        ValidatorUtil.validateDbRecord(examQuestion,String.format("question %s is not part of exam %s",questionId,examId));
        Option answerOptionByUser = optionService.getOption(answerSubmissionDto.getAnswerId());
        ValidatorUtil.validateDbRecord(answerOptionByUser,String.format("option %s is not part of question %s",answerId,questionId));
        UserAnswer answer = new UserAnswer();
        answer.setAnswer(answerOptionByUser);
        answer.setUserExam(userExam);
        answer.setExamQuestion(examQuestion);
        // @Todo: check if user has already answered this question and update
        return userAnswerRepository.save(answer);
    }
}
