package com.example.testspringapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.testspringapp.models.Course;
import com.example.testspringapp.repositories.CourseRepository;
import com.example.testspringapp.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll();
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		java.util.Date d = new java.util.Date();
		java.sql.Date sd = new java.sql.Date(d.getTime());
		course.setCreated(sd);
		course.setModified(sd);
		return courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("api/course/{id}")
	public Course findCourseById(@PathVariable("id") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@RequestMapping(value = "api/course/findCourseByCourseName/{courseName}", method = RequestMethod.GET)
	public List<Course> findCourseByCourseName(@PathVariable("courseName") String courseName) {
		String newCourseName = "%" + courseName + "%";
		return (List<Course>) courseRepository.findCourseByCourseName(newCourseName);
	}
}
