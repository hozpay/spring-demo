package com.example.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private static final String CACHE_DIR = "cache-data";

    @PostMapping
    public Map<String, String> cacheData(@RequestParam String key, @RequestParam String value) {

        File file = new File(CACHE_DIR, key);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(value);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to cache", e);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cached successfully");
        return response;
    }

    @GetMapping("/{key}")
    public Map<String, String> retrieveData(@PathVariable String key) {
        File file = new File(CACHE_DIR, key);
        if (!file.exists()) {
            throw new RuntimeException("Key not found in cache");
        }
        try {
            String value = Files.readString(file.toPath());
            Map<String, String> response = new HashMap<>();
            response.put("key", key);
            response.put("value", value);
            return response;
        } catch (IOException e) {
            throw new RuntimeException("Error reading from cache", e);
        }
    }
}
