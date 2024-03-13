package com.markj.onlinerecipes.controller;

import com.markj.onlinerecipes.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.markj.onlinerecipes.repository.UserRepository;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception{

        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist!=null){
            throw new Exception("user exist with "+ user.getEmail());
        }

        User savedUser = userRepository.save(user);

        return savedUser;
    }


    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {

        userRepository.deleteById(userId);

        return "User deleted successfully";
    }


    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {

        List<User> users = userRepository.findAll();

        return users;
    }
}
