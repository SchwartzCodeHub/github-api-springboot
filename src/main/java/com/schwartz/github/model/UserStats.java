package com.schwartz.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserStats {
    @JsonProperty("user_name")
    String username;
    @JsonProperty("display_name")
    String displayName;
    String avatar;
    @JsonProperty("geo_location")
    String geoLocation;
    String email;
    String url;
    @JsonProperty("created_at")
    String createdAt;// "Tue, 25 Jan 2011 18:44:36 GMT"
    Repository[] repos;
}
