package com.example.testspringapp.models;
import javax.persistence.*;

@Entity
public class TrueOrFalse extends Question {
	private boolean isTrue;

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
}
