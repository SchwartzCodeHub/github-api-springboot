package com.schwartz.github.mapper;

import com.schwartz.github.model.Repository;
import com.schwartz.github.model.User;
import com.schwartz.github.model.UserStats;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserStatsMapperTest {

    @Test
    void mapShouldPopulateAllFieldsFromUserAndRepos() {
        User user = new User();
        user.setAvatarUrl("http://avatar.com/img.png");
        user.setEmail("user@example.com");
        user.setLocation("Earth");
        user.setName("Alice");
        user.setUrl("http://github.com/alice");
        user.setLogin("alice");
        user.setCreatedAt("2024-01-01T10:15:30Z");

        Repository repo1 = new Repository();
        repo1.setName("repo1");
        Repository repo2 = new Repository();
        repo2.setName("repo2");
        Repository[] repos = { repo1, repo2 };

        UserStats stats = UserStatsMapper.map(user, repos);

        assertThat(stats.getAvatar()).isEqualTo("http://avatar.com/img.png");
        assertThat(stats.getEmail()).isEqualTo("user@example.com");
        assertThat(stats.getGeoLocation()).isEqualTo("Earth");
        assertThat(stats.getDisplayName()).isEqualTo("Alice");
        assertThat(stats.getUrl()).isEqualTo("http://github.com/alice");
        assertThat(stats.getUsername()).isEqualTo("alice");
        assertThat(stats.getRepos()).containsExactly(repo1, repo2);

        assertThat(stats.getCreatedAt()).isEqualTo("Mon, 1 Jan 2024 10:15:30 GMT");
    }

    @Test
    void convertIsoDateToRFCShouldReturnFormattedDate() {
        String isoDate = "2025-11-13T23:21:00Z";
        String result = UserStatsMapper.convertIsoDateToRFC(isoDate);

        assertThat(result).isEqualTo("Thu, 13 Nov 2025 23:21:00 GMT");
    }

    @Test
    void convertIsoDateToRFCShouldReturnInputWhenBlank() {
        String result = UserStatsMapper.convertIsoDateToRFC("");
        assertThat(result).isEqualTo("");
    }

    @Test
    void convertIsoDateToRFCShouldReturnInputWhenNull() {
        String result = UserStatsMapper.convertIsoDateToRFC(null);
        assertThat(result).isNull();
    }
}
