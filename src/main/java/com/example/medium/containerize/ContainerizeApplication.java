package com.example.medium.containerize;

import com.example.config.ConfigurationManagement;
import com.example.service.CpuBurnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example"} )
@Slf4j
public class ContainerizeApplication implements CommandLineRunner {
	@Autowired
	private CpuBurnerService cpuBurnerService;
	@Autowired
	private ConfigurationManagement config;

	public static void main(String[] args) {

		SpringApplication.run(ContainerizeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Calling cpu burner service for={}", config.getCpu());
		cpuBurnerService.burnCpu(config.getCpu());

	}
}
