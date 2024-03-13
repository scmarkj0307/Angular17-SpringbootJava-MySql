package com.markj.onlinerecipes.controller;

import com.markj.onlinerecipes.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.markj.onlinerecipes.repository.UserRepository;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
