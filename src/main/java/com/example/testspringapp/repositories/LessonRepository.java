package com.example.testspringapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

}
