package com.example.testspringapp.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	@Query(value = "SELECT * from widget WHERE widget.lesson_id = :lessonId", nativeQuery = true)
	List<Widget> findAllWidgetsForLesson(@Param("lessonId") int lessonId);
}
