package com.question.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.question.entities.Question;

public interface QuestionRepositories extends CrudRepository<Question, Long> {
	List<Question> findByQid(Long qid);
}
