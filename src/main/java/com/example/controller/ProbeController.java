package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProbeController {
    private int startupCount = 0;

    @GetMapping("/startup-probe")
    public ResponseEntity<?> startupCheck() {
        startupCount++;
        if (startupCount < 3) {
            log.info("startup probe failed - Attempt: {}", startupCount);
            return ResponseEntity.badRequest().body("startup probe failed ");
        }
        // Succeed in 3rd request
        log.info("startup probe passed - Attempt: {}", startupCount);

        return ResponseEntity.ok().body("startup probe passed ");
    }
}
