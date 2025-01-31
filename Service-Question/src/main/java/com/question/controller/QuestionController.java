package com.question.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.entities.Question;
import com.question.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	// create
	@PostMapping
	public Question crateQuestion(@RequestBody Question question)throws IOException {
		return questionService.createquestion(question);
	}

	// get all questions
	@GetMapping
	public Iterable<Question> getAllQuestions() {
		return questionService.getall();
	}

	// Get Question by id
	@GetMapping("/{id}")
	public Optional<Question> getQuestionByid(@PathVariable Long id) {
		return questionService.getQuestionByid(id);
	}

	// Get Questions for Quiz id
	@GetMapping("/quiz/{qid}")
	public List<Question> getQuestionforQuizByqid(@PathVariable Long qid) {
		return questionService.getQuestionForQuiz(qid);
	}
}
