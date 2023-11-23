package com.evan.HTTPCatClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HttpCatCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpCatCloneApplication.class, args);
	}

}
