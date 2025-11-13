package com.schwartz.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
    String login;
    @JsonProperty("avatar_url")
    String avatarUrl;
    String url;
    String name;
    String location;
    String email;
    @JsonProperty("created_at")
    String createdAt;
}
