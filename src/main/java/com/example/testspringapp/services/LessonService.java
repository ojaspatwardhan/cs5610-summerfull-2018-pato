package com.example.testspringapp.services;

import java.util.List;
import java.util.Optional;

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
import com.example.testspringapp.models.Lesson;
import com.example.testspringapp.models.Module;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.ModuleRepository;
import com.example.testspringapp.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("moduleId") int moduleId, @RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/api/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;
	}
	
	@DeleteMapping("api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}
}
