package com.quiz.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quiz.entities.Question;

@FeignClient(name="Service-Question")
public interface QuestionClient {
	@GetMapping("/question/quiz/{qid}")
	List<Question> getQuestionofQuiz(@PathVariable Long qid);
	@PostMapping("/question")
	Question createQuestions(@RequestBody Question question);
}
