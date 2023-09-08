package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

}