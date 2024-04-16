package com.quiz.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {
	private Long id;
	private String name;
	private Long qid;

}
