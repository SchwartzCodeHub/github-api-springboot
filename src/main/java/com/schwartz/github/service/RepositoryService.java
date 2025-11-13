package com.schwartz.github.service;

import com.schwartz.github.model.Repository;

public interface RepositoryService {
    public Repository[] getAllRepositoryForUser(String userId);
}