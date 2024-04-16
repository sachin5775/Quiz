package com.question.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.question.entities.Question;
import com.question.repositories.QuestionRepositories;
import com.question.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionRepositories questionRepositories;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final String jsonFilePath = "C:\\DEV\\ITALY\\API\\Microservices1\\Service-Question\\src\\main\\resources\\json\\Question.json";

	@Override
	public Question createquestion(Question question) throws IOException {
		List<Question> questions = objectMapper.readValue(new File(jsonFilePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
		questions.add(question);
		objectMapper.writeValue(new File(jsonFilePath), questions);
	 return questionRepositories.save(question);
	}
	
	@Override
	public Question createquestionforQuiz(Question question,Long id) throws IOException {
		List<Question> questions = objectMapper.readValue(new File(jsonFilePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
		questions.add(question);
		objectMapper.writeValue(new File(jsonFilePath), questions);
	 return questionRepositories.save(question);
	}

	@Override
	public Iterable<Question> getall() {
		return questionRepositories.findAll();
	}

	@Override
	public Optional<Question> getQuestionByid(Long id) {
		return questionRepositories.findById(id);
	}

	@Override
	public List<Question> getQuestionForQuiz(Long qid) {
		return questionRepositories.findByQid(qid);
	}
	
	public Iterable<Question> save(List<Question> questions) {
		return questionRepositories.saveAll(questions);
	}

}
