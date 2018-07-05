package com.example.testspringapp.models;

import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  @ManyToOne
  @JsonIgnore
  private Module module;
  @OneToMany(mappedBy="lesson", cascade=CascadeType.REMOVE)
  private List<Widget> widgets;
  @OneToMany(mappedBy="lesson", cascade=CascadeType.REMOVE)
  private List<Assignment> assignments;
  @OneToMany(mappedBy="lesson")
  private List<QuestionWidget> questionWidgets;
  
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Module getModule() {
	return module;
}
public void setModule(Module module) {
	this.module = module;
}
public List<Widget> getWidgets() {
	return widgets;
}
public void setWidgets(List<Widget> widgets) {
	this.widgets = widgets;
}
public List<Assignment> getAssignments() {
	return assignments;
}
public void setAssignments(List<Assignment> assignments) {
	this.assignments = assignments;
}
public List<QuestionWidget> getQuestionWidgets() {
	return questionWidgets;
}
public void setQuestionWidgets(List<QuestionWidget> questionWidgets) {
	this.questionWidgets = questionWidgets;
}

}
