package com.example.demo.services;

import com.example.demo.models.Exam;
import com.example.demo.models.User;
import com.example.demo.models.UserExam;
import com.example.demo.repositories.UserExamRepository;
import com.example.demo.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExamService {
    @Autowired
    private UserExamRepository userExamRepository;
    @Autowired
    private ExamService examService;

    public UserExam getUserExam(String userId, String examId) {
        return userExamRepository.findByUserIdAndExamId(userId, examId).orElse(null);
    }

    public UserExam registerNewUserToExam(User user,String examId) {
        // validate if exam exists
        Exam exam = examService.getExamById(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        UserExam userExam = new UserExam();
        userExam.setExam(exam);
        userExam.setUser(user);
        return userExamRepository.save(userExam);
    }

}
