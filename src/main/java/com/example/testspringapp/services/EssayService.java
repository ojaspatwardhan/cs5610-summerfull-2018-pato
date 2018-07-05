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
import com.example.testspringapp.models.Essay;
import com.example.testspringapp.repositories.ExamRepository;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.EssayRepository;
import com.example.testspringapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins="*")
public class EssayService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	EssayRepository essayRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/api/exam/{id}/essay")
	public Optional<Essay> getEssay(@PathVariable("id") int id) {
		Optional<Essay> data = essayRepository.findById(id);
		return data;
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public Essay createEssay(@PathVariable("examId") int examId, @RequestBody Essay newEssay) {
		Optional<Exam> data = examRepository.findById(examId);
		
		System.out.println("Essay object");
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssay.setExam(exam);
			return essayRepository.save(newEssay);
		}
		return null;
	}
	
	@PutMapping("/api/exam/{questionId}/essay")
	public Essay updateEssay(@PathVariable("questionId") int questionId, @RequestBody Essay updatedQuestion) {
		Optional<Question> data = questionRepository.findById(questionId);
		if(data.isPresent()) {
			Exam exam1 = data.get().getExam();
			updatedQuestion.setExam(exam1);
			return essayRepository.save(updatedQuestion);
		}
		return null;
	}
}
