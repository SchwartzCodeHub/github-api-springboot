package com.schwartz.github.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.schwartz.github.service.StatusService;

@Service
public class GithubStatusServiceImpl implements StatusService {

    private RestTemplate restTemplate;

    public GithubStatusServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isUp() {
        ResponseEntity<Object> response = restTemplate.getForEntity("https://api.github.com/status", Object.class);
        return response != null && response.getStatusCode().is2xxSuccessful();
    }
}
