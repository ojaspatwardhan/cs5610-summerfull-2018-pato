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
import com.example.testspringapp.models.MultipleChoice;
import com.example.testspringapp.repositories.EssayRepository;
import com.example.testspringapp.repositories.ExamRepository;
import com.example.testspringapp.repositories.FillInTheBlankRepository;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.MultipleChoiceRepository;
import com.example.testspringapp.repositories.QuestionRepository;
import com.example.testspringapp.repositories.TrueOrFalseRepository;

@RestController
@CrossOrigin(origins="*")
public class MultipleChoiceService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	MultipleChoiceRepository multipleChoiceRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/api/exam/{id}/choice")
	public Optional<MultipleChoice> getMCQ(@PathVariable("id") int id) {
		Optional<MultipleChoice> data = multipleChoiceRepository.findById(id);
		return data;
	}
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoice createMCQ(@PathVariable("examId") int examId, @RequestBody MultipleChoice newMCQ) {
		Optional<Exam> data = examRepository.findById(examId);
		
		System.out.println("Inside createMCQ");
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newMCQ.setExam(exam);
			return multipleChoiceRepository.save(newMCQ);
		}
		return null;
	}
	
	@PutMapping("/api/exam/{questionId}/choice")
	public MultipleChoice updateMCQ(@PathVariable("questionId") int questionId, @RequestBody MultipleChoice updatedQuestion) {
		Optional<Question> data = questionRepository.findById(questionId);
		if(data.isPresent()) {
			Exam exam1 = data.get().getExam();
			updatedQuestion.setExam(exam1);
			return multipleChoiceRepository.save(updatedQuestion);
		}
		return null;
	}
}
