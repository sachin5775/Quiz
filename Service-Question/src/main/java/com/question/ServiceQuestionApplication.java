package com.question;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.question.entities.Question;
import com.question.services.QuestionService;

@SpringBootApplication
public class ServiceQuestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceQuestionApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(QuestionService questionService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Question.json");
			try {
				List<Question> questions = mapper.readValue(inputStream, typeReference);
				questionService.save(questions);
				System.out.println("Question Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save Question: " + e.getMessage());
			}
		};
	}
}
