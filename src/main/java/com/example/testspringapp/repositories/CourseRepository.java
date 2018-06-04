package com.example.testspringapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.testspringapp.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query(value = "SELECT * FROM course where course.title LIKE :searchTerm", nativeQuery = true)
	List<Course> findCourseByCourseName(@Param("searchTerm") String searchTerm);
}
