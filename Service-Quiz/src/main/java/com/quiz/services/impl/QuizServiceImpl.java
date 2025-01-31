package com.quiz.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionClient questionClient;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final String jsonFilePath = "C:\\DEV\\ITALY\\API\\Microservices1\\Service-Quiz\\src\\main\\resources\\json\\quiz.json";

	@Override
	public Quiz createQuiz(Quiz quiz) throws IOException {
		List<Quiz> quizs = objectMapper.readValue(new File(jsonFilePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, Quiz.class));
		quizs.add(quiz);
		objectMapper.writeValue(new File(jsonFilePath), quizs);
		return quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> get() {
		List<Quiz> quizes = quizRepository.findAll();
		quizes.forEach(q -> q.setQuestions(questionClient.getQuestionofQuiz(q.getId())));
		return quizes;
	}

	@Override
	public Quiz getById(Long id) {
		Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Nothing to find"));
		quiz.setQuestions(questionClient.getQuestionofQuiz(quiz.getId()));
		return quiz;
	}

	@Override
	public Iterable<Quiz> save(List<Quiz> quizs) {
		return quizRepository.saveAll(quizs);
	}

	@Override
	public Question createQuestionForQuiz(Question question) {
		return questionClient.createQuestions(question);
	}

}
