package com.quiz.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;
import com.quiz.services.impl.QuizServiceImpl;


@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	// create
	@PostMapping
	public Quiz create(@RequestBody Quiz quiz) throws IOException {
		return quizService.createQuiz(quiz);
	}

	// get all
	@GetMapping
	public List<Quiz> getall() {
		return quizService.get();
	}

	// get by id
	@GetMapping("/{id}")
	public Quiz getOne(@PathVariable Long id) {
		return quizService.getById(id);
	}
	
	@PostMapping("/question/{id}")
	public Question createQuestionForQuiz(@PathVariable Long id,@RequestBody Question question) {
		question.setQid(id);
		return quizService.createQuestionForQuiz(question);
	}
}

