package com.example.testspringapp.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.TrueFalseType;
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

import com.example.testspringapp.models.Exam;
import com.example.testspringapp.models.Lesson;
import com.example.testspringapp.models.Question;
import com.example.testspringapp.models.TrueOrFalse;
import com.example.testspringapp.repositories.ExamRepository;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.MultipleChoiceRepository;
import com.example.testspringapp.repositories.TrueOrFalseRepository;
import com.example.testspringapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins="*")
public class TrueOrFalseService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TrueOrFalseRepository trueOrFalseRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/api/exam/{id}/truefalse")
	public Optional<TrueOrFalse> getTrueFalse(@PathVariable("id") int id) {
		Optional<TrueOrFalse> data = trueOrFalseRepository.findById(id);
		return data;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalse createTrueFalse(@PathVariable("examId") int examId, @RequestBody TrueOrFalse newTrueFalse) {
		Optional<Exam> data = examRepository.findById(examId);
		
		System.out.println("Inside createEssay");
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueFalse.setExam(exam);
			return trueOrFalseRepository.save(newTrueFalse);
		}
		return null;
	}
	
	@PutMapping("/api/exam/{questionId}/truefalse")
	public TrueOrFalse updateTrueFalse(@PathVariable("questionId") int questionId, @RequestBody TrueOrFalse newQuestion) {
		Optional<Question> data = questionRepository.findById(questionId);
		if(data.isPresent()) {
			Exam exam = data.get().getExam();
			newQuestion.setExam(exam);
			return trueOrFalseRepository.save(newQuestion);
		}
		return null;
	}
}
