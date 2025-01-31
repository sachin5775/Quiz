package com.quiz.services;

import java.io.IOException;
import java.util.List;

import com.quiz.entities.Question;
import com.quiz.entities.Quiz;


public interface QuizService {
	Quiz createQuiz(Quiz quiz) throws IOException;

	List<Quiz> get();

	Quiz getById(Long id);
	
	Iterable<Quiz> save(List<Quiz> quizs);
	
	Question createQuestionForQuiz(Question question);

}
