package com.example.medium.containerize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example"} )
public class ContainerizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainerizeApplication.class, args);
	}

}
