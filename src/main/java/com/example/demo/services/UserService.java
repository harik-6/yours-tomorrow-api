package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.UserExam;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserExamService userExamService;
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    public UserExam enrollToAnExam(String userId, String examId) {
        User existingUser = getUser(userId);
        ValidatorUtil.validateDbRecord(existingUser,"user not found with id "+userId);
        return userExamService.registerNewUserToExam(existingUser,examId);
    }

}
