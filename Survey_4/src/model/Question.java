package model;

import java.io.Serializable;

public class Question implements Serializable{

	private String question;
	private int answer;
	private int groupNumber;
	private int questionNumber;
	private boolean criticalValue;
	
	public Question(String q, int a, int gn, int qn, boolean c) {
		question = q;
		answer = a;
		groupNumber = gn;
		questionNumber = qn;
		criticalValue = c;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public boolean isCriticalValue() {
		return criticalValue;
	}
	
	
}
