package com.example.testspringapp.services;

import java.util.List;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

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

import com.example.testspringapp.models.Widget;
import com.example.testspringapp.models.Course;
import com.example.testspringapp.models.Lesson;
import com.example.testspringapp.repositories.WidgetRepository;
import com.example.testspringapp.repositories.LessonRepository;

@RestController
@CrossOrigin(origins =  "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>)widgetRepository.findAll();
	}

	@PostMapping("/api/widget/save/{lessonId}")
	public void saveAllWidgets(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> widgets) {
//		widgetRepository.deleteAll();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			for(Widget widget : widgets) {
				 widget.setLesson(lesson);
				 widgetRepository.save(widget);
			}
		}
	}
	
	@GetMapping("api/widget/findWidgetById/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/widget/{lessonId}")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		return (List<Widget>)widgetRepository.findAllWidgetsForLesson(lessonId);
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
