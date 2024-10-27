package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.configmap")
@Data
public class ConfigurationManagement {
    private int value1;
    private int value2;
    private String value3;
    private String value4;
    private int cpu;
}
