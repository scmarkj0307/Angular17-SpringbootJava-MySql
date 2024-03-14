package com.markj.onlinerecipes.service;

import com.markj.onlinerecipes.model.User;

public interface UserService {
    public User findUserById(Long userId) throws Exception;
}
