package com.schwartz.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GithubUserDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubUserDataApplication.class, args);
	}

}
