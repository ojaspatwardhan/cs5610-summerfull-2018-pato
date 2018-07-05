package com.example.testspringapp.models;

import javax.persistence.*;

@Entity
public class FillInTheBlank extends Question {
	private String blanks;

	public String getBlanks() {
		return blanks;
	}

	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}
}
