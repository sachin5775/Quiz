package com.question.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.question.entities.Question;

public interface QuestionService {
	Question createquestion(Question question) throws IOException;
	Question createquestionforQuiz(Question question,Long id) throws IOException;
	
	Iterable<Question> getall();

	Optional<Question> getQuestionByid(Long id);
	
	List<Question> getQuestionForQuiz(Long qid);
	
	Iterable<Question> save(List<Question> questions);
}
