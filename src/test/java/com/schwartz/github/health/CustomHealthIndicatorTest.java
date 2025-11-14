package com.schwartz.github.health;

import com.schwartz.github.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomHealthIndicatorTest {

    private StatusService statusService;
    private CustomHealthIndicator healthIndicator;

    @BeforeEach
    void setUp() {
        statusService = mock(StatusService.class);
        healthIndicator = new CustomHealthIndicator(statusService);
    }

    @Test
    void healthShouldReturnUpWhenServiceIsUp() {
        when(statusService.isUp()).thenReturn(true);

        Health health = healthIndicator.health();

        assertThat(health.getStatus().getCode()).isEqualTo("UP");
        assertThat(health.getDetails()).containsEntry("Github", "Available");
    }

    @Test
    void healthShouldReturnDownWhenServiceIsDown() {
        when(statusService.isUp()).thenReturn(false);

        Health health = healthIndicator.health();

        assertThat(health.getStatus().getCode()).isEqualTo("DOWN");
        assertThat(health.getDetails()).containsEntry("Github", "Unavailable");
    }

    @Test
    void healthShouldReturnDownWhenServiceThrowsException() {
        when(statusService.isUp()).thenThrow(new RuntimeException("Service error"));

        Health health = healthIndicator.health();

        assertThat(health.getStatus().getCode()).isEqualTo("DOWN");
        assertThat(health.getDetails()).containsEntry("Github", "Error during check");
    }
}
