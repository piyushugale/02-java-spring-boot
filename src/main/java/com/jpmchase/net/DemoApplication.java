package com.jpmchase.net;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}


/*
DemoApplication = application entry point
		SpringApplication.run(...) = starts the Spring context and auto-configures beans
		In short: the entry point is the class with main() that launches your Spring Boot app.

 */