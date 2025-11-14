package com.schwartz.github.service;

import org.springframework.http.ResponseEntity;

import com.schwartz.github.model.Repository;

public interface RepositoryService {
    public ResponseEntity<Repository[]> getAllRepositoryForUser(String userId);
}