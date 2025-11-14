package com.schwartz.github.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import com.schwartz.github.model.Repository;
import com.schwartz.github.model.User;
import com.schwartz.github.model.UserStats;

public class UserStatsMapper {
    public static UserStats map(User user, Repository[] repos) {
        UserStats userStats = new UserStats();
        userStats.setAvatar(user.getAvatarUrl());
        userStats.setEmail(user.getEmail());
        userStats.setGeoLocation(user.getLocation());
        userStats.setDisplayName(user.getName());
        userStats.setUrl(user.getUrl());
        userStats.setUsername(user.getLogin());
        userStats.setCreatedAt(convertIsoDateToRFC(user.getCreatedAt()));
        userStats.setRepos(repos);
        return userStats;
    }

    static String convertIsoDateToRFC(String iso8601String) {
        if (StringUtils.hasText(iso8601String)) {
            Instant instant = Instant.parse(iso8601String);
            ZonedDateTime zdt = instant.atZone(ZoneId.of("GMT"));
            DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
            return formatter.format(zdt);
        }
        return iso8601String;
    }
}
