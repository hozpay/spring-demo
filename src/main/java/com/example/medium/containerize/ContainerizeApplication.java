package com.example.medium.containerize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import util.Person;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ContainerizeApplication {

	public static void main(String[] args) {
		// Original object
		List<String> hobbies = new ArrayList<>();
		hobbies.add("Running");
		hobbies.add("swimming");

		Person originalPerson = new Person("John", hobbies);

		// Shallow copy using copy constructor
		Person shallowCopyPerson = new Person(originalPerson);

		// Modifying the shallow copy
		shallowCopyPerson.hobbies.add("playing");

		// Original object remains unchanged
		System.out.println("Original util.Person: " + originalPerson.hobbies);
		System.out.println("Shallow Copy util.Person: " + shallowCopyPerson.hobbies);



		SpringApplication.run(ContainerizeApplication.class, args);
	}

}
