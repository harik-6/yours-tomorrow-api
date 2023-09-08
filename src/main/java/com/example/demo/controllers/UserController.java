package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

}
