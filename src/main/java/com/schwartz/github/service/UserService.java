package com.schwartz.github.service;

import org.springframework.http.ResponseEntity;

import com.schwartz.github.model.User;

public interface UserService {
    public ResponseEntity<User> getUserData(String userId);
}