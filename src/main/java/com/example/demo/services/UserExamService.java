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
    private UserService userService;
    @Autowired
    private ExamService examService;

    public UserExam getUserExam(String userExamId) {
        return userExamRepository.findById(userExamId).orElse(null);
    }

    public UserExam getUserExam(String userId, String examId) {
        return userExamRepository.findByUserIdAndExamId(userId, examId).orElse(null);
    }

    public UserExam joinExam(String examId, String userId) {
        // validate if user exists
        User user = userService.getUser(userId);
        ValidatorUtil.validateDbRecord(user,"user not found with id "+userId);
        // validate if exam exists
        Exam exam = examService.getExam(examId);
        ValidatorUtil.validateDbRecord(exam,"exam not found with id "+examId);
        UserExam userExam = new UserExam();
        userExam.setExam(exam);
        userExam.setUser(user);
        return userExamRepository.save(userExam);
    }

}
