package controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class RestApplication {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("Requested user is: {}", name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/user")
    public List<String> getUser() {
        log.info("User details are requested via GET method");
        return List.of("user1", "user2");
    }

    @GetMapping("/userid")
    public List<Integer> getUserId() {
        log.info("UserIds are requested via GET method");
        return List.of(125603, 121);
    }

}
