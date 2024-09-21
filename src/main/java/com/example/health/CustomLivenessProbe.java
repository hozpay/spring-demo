package com.example.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomLivenessProbe implements HealthIndicator {
    private int livenessRequestCount = 0;

    @Override
    public Health health() {
        livenessRequestCount++;
        // Fail liveness check for the first 2 requests
        if (livenessRequestCount < 3) {
            log.info("Liveness failed - Attempt: {}", livenessRequestCount);
            return Health.down().withDetail("Liveness", "Not ready yet").build();
        }
        // Succeed afterwards
        log.info("Liveness passed - Attempt: {}", livenessRequestCount);
        return Health.up().withDetail("Liveness", "I'm alive!").build();
    }

}
