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

}
