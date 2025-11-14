package com.schwartz.github.controller;

import org.springframework.web.bind.annotation.RestController;

import com.schwartz.github.mapper.UserStatsMapper;
import com.schwartz.github.model.Repository;
import com.schwartz.github.model.User;
import com.schwartz.github.model.UserStats;
import com.schwartz.github.service.RepositoryService;
import com.schwartz.github.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users/{userId}")
public class UserStatsController {

    private UserService userService;
    private RepositoryService repositoryService;

    public UserStatsController(UserService userService, RepositoryService repositoryService) {
        this.userService = userService;
        this.repositoryService = repositoryService;
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getUserStats(@PathVariable("userId") String userId) {
        if (StringUtils.hasText(userId)) {
            User userData = this.userService.getUserData(userId).getBody();
            Repository[] repositories = this.repositoryService.getAllRepositoryForUser(userId).getBody();
            UserStats userStats = UserStatsMapper.map(userData, repositories);
            return ResponseEntity.ok(userStats);
        } else {
            return ResponseEntity.badRequest().body("User ID is required");
        }
    }

}