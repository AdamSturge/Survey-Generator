package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionList implements Serializable {

	private HashMap<Integer,ArrayList<Question>> groupMap; //list of questions for a given group
	private HashMap<Integer,ArrayList<Question>> answerMap; //list of questions for a given answer
	private HashMap<Integer,Question> questionMap; //maps questonNumber to questions

	public QuestionList(ArrayList<Question> questions) {
		groupMap = new HashMap<Integer,ArrayList<Question>>();
		answerMap = new HashMap<Integer,ArrayList<Question>>();
		questionMap = new HashMap<Integer,Question>();
		addQuestions(questions);
	}


	//this is only used internally so far so Adam made it private
	private void addQuestion(Question q) {
		int questionNumber = q.getQuestionNumber();
		questionMap.put(questionNumber,q);
		int groupNumber = q.getGroupNumber();
		if(groupMap.containsKey(groupNumber)) {
			groupMap.get(groupNumber).add(q);
		} else {
			ArrayList<Question> groupList = new ArrayList<Question>();
			groupList.add(q);
			groupMap.put(groupNumber, groupList);
		}

		int answer = q.getAnswer();
		if(answerMap.containsKey(answer)) {
			answerMap.get(answer).add(q);
		} else {
			ArrayList<Question> answerList = new ArrayList<Question>();
			answerList.add(q);
			answerMap.put(answer, answerList);

		}
	}

	public void addQuestions(ArrayList<Question> questions) {
		for(Question q: questions) {
			this.addQuestion(q);
		}
	}

	public void clearGroupMap() {
		groupMap = new HashMap<Integer,ArrayList<Question>>();
	}

	public void clearAnswerMap() {
		answerMap = new HashMap<Integer,ArrayList<Question>>();
	}

	public void clearGroupList(int groupNumber){
		groupMap.remove(groupNumber);
	}
	public void clearAnswerList(int answerNumber){
		answerMap.remove(answerNumber);
	}

	public HashMap<Integer, ArrayList<Question>> getGroupMap() {
		return groupMap;
	}

	public HashMap<Integer, ArrayList<Question>> getAnswerMap() {
		return answerMap;
	}

	public int getNumColumns(){
		int col = -1;
		for(Integer i: answerMap.keySet()) {
			if (i > col){
				col = i;
			}
		}
		return col;
	}

	public String toString(){
		String output = "" ;
		ArrayList<Question> questionArray;
		Question question;
		int questionArraySize;
		int totalGroup = groupMap.keySet().size(); //assumes starts at group 1
		for(int i = 1; i <= totalGroup; i++){
			output += "\"group" + i + "\":{ \"questions\":\"";
			questionArray = groupMap.get(i);
			questionArraySize = questionArray.size();
			for(int j = 0; j < questionArraySize; j++){
				question = questionArray.get(j);
				output += question.getQuestion() + "#!#";
			}
			output += "\"";
			if(i != totalGroup)
				output += "},";
			else
				output +="}}";
		}

		System.out.println(output);
		return output;

	}
	

	public Question getQuestion(int questionNumber){
		return questionMap.get(questionNumber);
	}

}