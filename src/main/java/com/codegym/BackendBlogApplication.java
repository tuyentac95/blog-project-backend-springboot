package com.codegym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackendBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendBlogApplication.class, args);
	}

}
