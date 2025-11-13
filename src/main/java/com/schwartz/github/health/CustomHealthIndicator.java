package com.schwartz.github.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.schwartz.github.service.StatusService;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private StatusService statusService;

    public CustomHealthIndicator(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    public Health health() {
        try {
            boolean isDependentServiceUp = statusService.isUp();

            if (isDependentServiceUp) {
                return Health.up().withDetail("Github", "Available").build();
            } else {
                return Health.down().withDetail("Github", "Unavailable").build();
            }
        } catch (Exception e) {
            return Health.down(e).withDetail("Github", "Error during check").build();
        }
    }
}