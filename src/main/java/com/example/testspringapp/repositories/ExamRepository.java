package com.example.testspringapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer> {
	@Query(value = "SELECT * FROM question_widget WHERE question_widget.lesson_id = :lessonId", nativeQuery = true)
	List<Exam> findExamsByLesson(@Param("lessonId") int lessonId);
}
