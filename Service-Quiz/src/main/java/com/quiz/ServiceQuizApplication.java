package com.quiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;

@SpringBootApplication
@EnableFeignClients
public class ServiceQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceQuizApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(QuizService quizService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Quiz>> typeReference = new TypeReference<List<Quiz>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/quiz.json");
			try {
				List<Quiz> quizs = mapper.readValue(inputStream, typeReference);
				quizService.save(quizs);
				System.out.println("Quiz Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save Quiz: " + e.getMessage());
			}
		};
	}
	
}
