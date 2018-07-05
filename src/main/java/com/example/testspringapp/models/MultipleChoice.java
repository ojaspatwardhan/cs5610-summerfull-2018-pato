package com.example.testspringapp.models;

import javax.persistence.*;

@Entity
public class MultipleChoice extends Question {
	private String options;
	private String correctOption;
	
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
}
