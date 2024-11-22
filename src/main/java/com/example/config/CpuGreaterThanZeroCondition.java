package com.example.config;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CpuGreaterThanZeroCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String cpuBurn = env.getProperty("CPU_BURN");
        return cpuBurn != null; // Only matches if CPU_BURN is explicitly set
    }
}
