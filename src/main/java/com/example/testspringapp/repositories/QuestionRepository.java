package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.testspringapp.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
