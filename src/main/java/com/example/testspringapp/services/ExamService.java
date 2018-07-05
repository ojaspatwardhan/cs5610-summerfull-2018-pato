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
import com.example.testspringapp.models.TrueOrFalse;
import com.example.testspringapp.models.FillInTheBlank;
import com.example.testspringapp.models.Essay;
import com.example.testspringapp.repositories.ExamRepository;
import com.example.testspringapp.repositories.LessonRepository;
import com.example.testspringapp.repositories.MultipleChoiceRepository;
import com.example.testspringapp.repositories.TrueOrFalseRepository;
import com.example.testspringapp.repositories.FillInTheBlankRepository;
import com.example.testspringapp.repositories.EssayRepository;
import com.example.testspringapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins="*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	MultipleChoiceRepository multipleChoiceRepository; 
	@Autowired
	TrueOrFalseRepository trueOrFalseRepository;
	@Autowired
	EssayRepository essayRepository;
	@Autowired
	FillInTheBlankRepository fillInTheBlankRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll();
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(@PathVariable("lessonId") int lessonId, @RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId) {
		return examRepository.findExamsByLesson(lessonId);
	}
	
	@GetMapping("/api/exam/{examId}/questions")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
//	@GetMapping("/api/exam/{id}/choice")
//	public Optional<MultipleChoice> getMCQ(@PathVariable("id") int id) {
//		Optional<MultipleChoice> data = multipleChoiceRepository.findById(id);
//		return data;
//	}
	
//	@GetMapping("/api/exam/{id}/essay")
//	public Optional<Essay> getEssay(@PathVariable("id") int id) {
//		Optional<Essay> data = essayRepository.findById(id);
//		return data;
//	}
	
//	@GetMapping("/api/exam/{id}/truefalse")
//	public Optional<TrueOrFalse> getTrueFalse(@PathVariable("id") int id) {
//		Optional<TrueOrFalse> data = trueOrFalseRepository.findById(id);
//		return data;
//	}
	
//	@GetMapping("/api/exam/{id}/blank")
//	public Optional<FillInTheBlank> getFillInTheBlank(@PathVariable("id") int id) {
//		Optional<FillInTheBlank> data = fillInTheBlankRepository.findById(id);
//		return data;
//	}
	
//	@PostMapping("/api/exam/{examId}/choice")
//	public MultipleChoice createMCQ(@PathVariable("examId") int examId, @RequestBody MultipleChoice newMCQ) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		System.out.println("Inside createMCQ");
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newMCQ.setExam(exam);
//			return multipleChoiceRepository.save(newMCQ);
//		}
//		return null;
//	}
	
//	@PostMapping("/api/exam/{examId}/essay")
//	public Essay createEssay(@PathVariable("examId") int examId, @RequestBody Essay newEssay) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		System.out.println("Essay object");
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newEssay.setExam(exam);
//			return essayRepository.save(newEssay);
//		}
//		return null;
//	}
	
//	@PostMapping("/api/exam/{examId}/truefalse")
//	public TrueOrFalse createTrueFalse(@PathVariable("examId") int examId, @RequestBody TrueOrFalse newTrueFalse) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		System.out.println("Inside createEssay");
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newTrueFalse.setExam(exam);
//			return trueOrFalseRepository.save(newTrueFalse);
//		}
//		return null;
//	}
	
//	@PostMapping("/api/exam/{examId}/blank")
//	public FillInTheBlank createBlank(@PathVariable("examId") int examId, @RequestBody FillInTheBlank newBlank) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		System.out.println(newBlank);
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newBlank.setExam(exam);
//			return fillInTheBlankRepository.save(newBlank);
//		}
//		return null;
//	}
	
	@DeleteMapping("/api/exam/{questionId}/questions")
	public void deleteQuestion(@PathVariable("questionId") int questionId) {
		questionRepository.deleteById(questionId);
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
	
//	@PutMapping("/api/exam/{questionId}/essay")
//	public Essay updateEssay(@PathVariable("questionId") int questionId, @RequestBody Essay updatedQuestion) {
//		Optional<Question> data = questionRepository.findById(questionId);
//		if(data.isPresent()) {
//			Exam exam1 = data.get().getExam();
//			updatedQuestion.setExam(exam1);
//			return essayRepository.save(updatedQuestion);
//		}
//		return null;
//	}
	
//	@PutMapping("/api/exam/{questionId}/choice")
//	public MultipleChoice updateMCQ(@PathVariable("questionId") int questionId, @RequestBody MultipleChoice updatedQuestion) {
//		Optional<Question> data = questionRepository.findById(questionId);
//		if(data.isPresent()) {
//			Exam exam1 = data.get().getExam();
//			updatedQuestion.setExam(exam1);
//			return multipleChoiceRepository.save(updatedQuestion);
//		}
//		return null;
//	}
	
//	@PutMapping("/api/exam/{questionId}/truefalse")
//	public TrueOrFalse updateTrueFalse(@PathVariable("questionId") int questionId, @RequestBody TrueOrFalse newQuestion) {
//		Optional<Question> data = questionRepository.findById(questionId);
//		if(data.isPresent()) {
//			Exam exam = data.get().getExam();
//			newQuestion.setExam(exam);
//			return trueOrFalseRepository.save(newQuestion);
//		}
//		return null;
//	}
	
//	@PutMapping("/api/exam/{questionId}/blank")
//	public FillInTheBlank updateFillInTheBlank(@PathVariable("questionId") int questionId, @RequestBody FillInTheBlank newQuestion) {
//		Optional<Question> data = questionRepository.findById(questionId);
//		if(data.isPresent()) {
//			Exam exam = data.get().getExam();
//			newQuestion.setExam(exam);
//			return fillInTheBlankRepository.save(newQuestion);
//		}
//		return null;
//	}
}
