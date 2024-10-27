package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CpuBurnerService {

    /**
     * Consumes CPU by performing calculations for the configured time.
     */
    public void burnCpu(final int cpu) {
        log.info("trying to burn={} mCPU", cpu);
        // Convert CPU milli values (m) to percentage load
        int cpuLoadPercent = Math.min(100, cpu / 10); // Convert 100m to 10% load

        // Target duration for which to sustain the load (e.g., 5 seconds)
        long durationMillis = 5000;
        long endTime = System.currentTimeMillis() + durationMillis;

        log.info("Starting CPU burn at ~ ={} % CPU load for ={} seconds.", cpuLoadPercent, durationMillis);

        while (System.currentTimeMillis() < endTime) {
            long busyTime = (long) (10 * cpuLoadPercent); // Busy time in each 100ms interval
            long idleTime = cpu - busyTime; // Idle time in each 100ms interval

            long start = System.currentTimeMillis();

            // Busy-wait loop for the calculated busyTime
            while (System.currentTimeMillis() - start < busyTime) {
                Math.sqrt(Math.random()); // Dummy calculation to keep CPU busy
            }

            // Sleep for the remaining idleTime
            try {
                Thread.sleep(idleTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        log.info("CPU burn completed.");
    }
}
