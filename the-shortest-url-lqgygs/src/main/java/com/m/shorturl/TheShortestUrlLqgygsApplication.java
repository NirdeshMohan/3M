package com.m.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.m.shorturl.*")
@EnableWebMvc
public class TheShortestUrlLqgygsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheShortestUrlLqgygsApplication.class, args);
	}

}
