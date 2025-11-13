package com.schwartz.github.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.schwartz.github.model.User;
import com.schwartz.github.service.UserService;

@Service
public class GithubUserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    public GithubUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("user")
    public User getUserData(String userId) {
        String uri = "https://api.github.com/users/{userId}";
        ResponseEntity<User> response = restTemplate.getForEntity(uri, User.class, userId);
        if (response != null) {
            return response.getBody();
        } else {
            throw new RuntimeException();// TODO - create exception and exception handler
        }
    }
}
