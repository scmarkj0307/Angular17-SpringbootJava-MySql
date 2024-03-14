package com.markj.onlinerecipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markj.onlinerecipes.model.User;
import com.markj.onlinerecipes.repository.UserRepository;

import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("user not found with id: " + userId);
    }
}
