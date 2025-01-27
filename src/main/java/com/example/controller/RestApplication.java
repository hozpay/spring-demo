package com.example.controller;

import com.example.config.ConfigurationManagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class RestApplication {
    @Autowired
    private ConfigurationManagement config;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("Requested user is: {}", name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/user")
    public List<String> getUser() {
        log.info("User details are requested via GET method. Values from env variable are={} and={}"
                , config.getValue3(), config.getValue4());

        return List.of("user1", "user2", config.getValue3(), config.getValue4());
    }

    @GetMapping("/userid")
    public List<Integer> getUserId() {
        log.info("UserIds are requested via GET method. Values from env variable are={} and={}"
                , config.getValue1(), config.getValue2());
        return List.of(125603, 121, config.getValue1(), config.getValue2());
    }

    @GetMapping("/delay")
    public String delay(@RequestParam(value = "sec", defaultValue = "5") int sec) {

        if ( sec > 301 ) {
            log.info("Requested delay is: {} seconds but it is truncated to 5.", sec);
            sec = 5;
        }

        log.info("Requested delay is: {} seconds", sec);

        try
        {
            Thread.sleep(sec*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        String response = "API call expired in " + sec + " seconds";

        return response;
    }
}


