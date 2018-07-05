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
import com.example.testspringapp.models.FillInTheBlank;
import com.example.testspringapp.repositories.ExamRepository;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.FillInTheBlankRepository;
import com.example.testspringapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins="*")
public class FillInTheBlankService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	FillInTheBlankRepository fillInTheBlankRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/api/exam/{id}/blank")
	public Optional<FillInTheBlank> getFillInTheBlank(@PathVariable("id") int id) {
		Optional<FillInTheBlank> data = fillInTheBlankRepository.findById(id);
		return data;
	}
	
	@PostMapping("/api/exam/{examId}/blank")
	public FillInTheBlank createBlank(@PathVariable("examId") int examId, @RequestBody FillInTheBlank newBlank) {
		Optional<Exam> data = examRepository.findById(examId);
		
		System.out.println(newBlank);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newBlank.setExam(exam);
			return fillInTheBlankRepository.save(newBlank);
		}
		return null;
	}
	
	@PutMapping("/api/exam/{questionId}/blank")
	public FillInTheBlank updateFillInTheBlank(@PathVariable("questionId") int questionId, @RequestBody FillInTheBlank newQuestion) {
		Optional<Question> data = questionRepository.findById(questionId);
		if(data.isPresent()) {
			Exam exam = data.get().getExam();
			newQuestion.setExam(exam);
			return fillInTheBlankRepository.save(newQuestion);
		}
		return null;
	}
}
