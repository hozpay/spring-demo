package com.example.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomReadinessProbe implements HealthIndicator {

    private int readinessRequestCount = 0;

    @Override
    public Health health() {
        readinessRequestCount++;
        // Fail readiness check for the first 3 requests
        if (readinessRequestCount < 4) {
            log.info("Readiness failed - Attempt: {}", readinessRequestCount);
            return Health.down().withDetail("Readiness", "Not ready yet").build();
        }
        // Succeed after 3rd request
        log.info("Readiness passed - Attempt: {}", readinessRequestCount);
        return Health.up().withDetail("Readiness", "I'm ready!").build();
    }
}

