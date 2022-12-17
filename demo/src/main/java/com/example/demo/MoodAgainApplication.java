package com.example.demo;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class MoodAgainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodAgainApplication.class, args);
	}

}
