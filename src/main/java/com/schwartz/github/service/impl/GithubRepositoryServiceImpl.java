package com.schwartz.github.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.schwartz.github.model.Repository;
import com.schwartz.github.service.RepositoryService;

@Service
public class GithubRepositoryServiceImpl implements RepositoryService {

    private final RestTemplate restTemplate;

    public GithubRepositoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("repository")
    public ResponseEntity<Repository[]> getAllRepositoryForUser(String userId) {
        String uri = "https://api.github.com/users/{userId}/repos";
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(uri, Repository[].class, userId);
        if (response != null) {
            return response;
        } else {
            throw new RestClientResponseException(
                    "No content found for repository for user" + userId,
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    null,
                    new byte[0],
                    null);
        }
    }
}
