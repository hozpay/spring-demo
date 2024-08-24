package com.example.medium.containerize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import util.Person;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = { "controller"} )
public class ContainerizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainerizeApplication.class, args);
	}

}
