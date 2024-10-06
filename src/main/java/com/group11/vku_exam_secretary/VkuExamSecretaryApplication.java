package com.group11.vku_exam_secretary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.group11.models"})
@ComponentScan(basePackages = {"com.group11.controller","com.group11.repository","com.group11.service","com.group11.config,com.group11.exceptions"})
@EnableJpaRepositories(basePackages = "com.group11.repository")

@SpringBootApplication
public class VkuExamSecretaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkuExamSecretaryApplication.class, args);
	}

}
