package com.schwartz.github.controller;

import com.schwartz.github.model.Repository;
import com.schwartz.github.model.User;
import com.schwartz.github.model.UserStats;
import com.schwartz.github.service.RepositoryService;
import com.schwartz.github.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.assertj.core.util.Arrays;

public class UserStatsControllerTest {

    private UserService userService;
    private RepositoryService repositoryService;
    private UserStatsController controller;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        repositoryService = mock(RepositoryService.class);
        controller = new UserStatsController(userService, repositoryService);
    }

    @Test
    void getUserStatsShouldReturnBadRequestWhenUserIdIsEmpty() {
        ResponseEntity<?> response = controller.getUserStats("");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("User ID is required");
    }

    @Test
    void getUserStatsShouldReturnUserStatsWhenUserIdIsValid() {
        String userId = "octocat";

        User mockUser = new User();
        mockUser.setLogin(userId);

        Repository[] mockRepos = new Repository[1];
        mockRepos[0] = new Repository();
        mockRepos[0].setName("repo1");

        when(userService.getUserData(userId)).thenReturn(ResponseEntity.ok(mockUser));
        when(repositoryService.getAllRepositoryForUser(userId)).thenReturn(ResponseEntity.ok(mockRepos));

        ResponseEntity<?> response = controller.getUserStats(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(UserStats.class);

        UserStats stats = (UserStats) response.getBody();
        assertThat(stats).isNotNull();
        // Optionally verify mapping logic
        assertThat(stats.getUsername()).isEqualTo(userId);
    }

    @Test
    void getUserStatsShouldHandleEmptyListOfRepositories() {
        String userId = "octocat";

        User mockUser = new User();
        mockUser.setLogin(userId);

        when(userService.getUserData(userId)).thenReturn(ResponseEntity.ok(mockUser));
        when(repositoryService.getAllRepositoryForUser(userId))
                .thenReturn(ResponseEntity.ok(Arrays.array()));

        ResponseEntity<?> response = controller.getUserStats(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(UserStats.class);
    }
}
