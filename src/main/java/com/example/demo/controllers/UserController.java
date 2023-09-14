package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping("{userId}/enroll/exam/{examId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void enrollToAnExam(@PathVariable("userId") String userId, @PathVariable("examId") String examId) {
        userService.enrollToAnExam(userId, examId);
    }

    @DeleteMapping("{userId}/withdraw/exam/{examId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void withdrawFromAndExam(@PathVariable("userId") String userId, @PathVariable("examId") String examId) {
        //@Todo need to implement withdraw from exam
        throw new RuntimeException("Not implemented yet");
    }
}
